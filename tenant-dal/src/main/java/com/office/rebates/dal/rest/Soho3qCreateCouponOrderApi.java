package com.office.rebates.dal.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.office.rebates.dal.dataobj.SalesPeople;
import com.office.rebates.model.Soho3qOrder;
import com.office.rebates.model.Soho3qUserLoginResult;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.request.CreateCouponOrderRequest;
import com.office.rebates.model.request.CreateOrderRequest;
import com.office.rebates.model.request.SelectProductModel;
import com.office.rebates.model.request.SelectProductRequest;
import com.office.rebates.model.request.Soho3qOrderProdSummary;
import com.office.rebates.model.request.SubmitCouponOrderRequest;
import com.office.rebates.model.request.SubmitOrderRequest;
import com.office.rebates.model.OrderItem;



public class Soho3qCreateCouponOrderApi {
	//@Autowired
	//private Soho3qTokenApi soho3qTokenApi;
	
	@Autowired
	private Soho3qCheckOrderApi soho3qCheckOrderApi;
	
	@Autowired
	private Soho3qUserLoginApi soho3qUserLoginApi;
	
	static Logger logger = Logger.getLogger(Soho3qCreateCouponOrderApi.class);	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	private String url;
	private int timeOut;
	
	public Long createSoho3qCouponOrder(CreateCouponOrderRequest request,SalesPeople people) throws RebatesException {
		logger.info("creating coupon order by request:"+JSON.toJSONString(request)+" by sales people:"+JSON.toJSONString(people));
		
		//创建token
		//String token=soho3qTokenApi.getToken(people.getUserName(), people.getUserPassword());
		
		Soho3qUserLoginResult login=soho3qUserLoginApi.login(people.getUserName(), people.getUserPassword());
		logger.info("got login info:"+JSON.toJSONString(login));
			
		//下单
		SubmitCouponOrderRequest submitCouponOrderRequest=new SubmitCouponOrderRequest();
		submitCouponOrderRequest.setCustomerCompany(request.getCustomerCompany());
		submitCouponOrderRequest.setCustomerName(request.getCustomerName());
		submitCouponOrderRequest.setCustomerPhone(request.getCustomerMobile());
		submitCouponOrderRequest.setItems(request.getCouponOrderItems());
		

		boolean success=createSoho3qCouponOrder(login.getToken(),login.getSid(),submitCouponOrderRequest);
		if(!success){
			logger.error("failt to create coupon soho3q order");
			throw new RebatesException(Messages.FAIL_TO_CREATE_SOHO3Q_COUPON_ORDER_CODE,Messages.FAIL_TO_CREATE_SOHO3Q_COUPON_ORDER_MSG);
		}
		
		//查询订单id
		Long soho3qId=null;
		List<Soho3qOrder> soho3qOrders=soho3qCheckOrderApi.getMostRecentSoho3qOrders(login.getToken(),login.getSid(),3);
		if(soho3qOrders==null||soho3qOrders.isEmpty()){
			logger.error("failed to get soho3q orders");
			throw new RebatesException(Messages.FAIL_TO_GET_SOHO3Q_ORDER_CODE,Messages.FAIL_TO_GET_SOHO3Q_ORDER_MSG);
		}else{
			boolean found=false;
			for(Soho3qOrder soho3qOrder:soho3qOrders){
				if(request.getCustomerMobile().equals(soho3qOrder.getCustomerAccount())&&
						request.getCustomerName().equals(soho3qOrder.getCustomerName())){
					soho3qId=soho3qOrder.getSoho3qOrderId();
					found=true;
					break;
				}
				//not find matching soho3q order
				if(!found){
					logger.error("not find matching soho3q order in last 3 soho3q orders");
					throw new RebatesException(Messages.NOT_FOUND_MATCHING_SOHO3Q_ORDER_CODE,Messages.NOT_FOUND_MATCHING_SOHO3Q_ORDER_MSG);		
				}						
			}
		}
		return soho3qId;
	}
	
	private boolean createSoho3qCouponOrder(String token,String sid, SubmitCouponOrderRequest submitCouponOrderRequest) throws RebatesException {
		boolean success=false;
		//String submitOrderUrl="http://m.soho3q.com/salesvc/ajax/booking/submitcustomorder";
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpPost request = new HttpPost(url);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();		
		BasicClientCookie cookie = new BasicClientCookie("token", token);
		cookie.setDomain(".soho3q.com");
		cookie.setPath("/");
		
		BasicClientCookie cookie2 = new BasicClientCookie("sid", sid);
		cookie2.setDomain(".soho3q.com");
		cookie2.setPath("/");
		
		CookieStore cookieStore = new BasicCookieStore();
		cookieStore.addCookie(cookie);	
		cookieStore.addCookie(cookie2);	
		CloseableHttpClient closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();  
		request.setConfig(config);	  
		CloseableHttpResponse response=null;	
		try {
			request.addHeader("content-type", "application/json;charset=UTF-8");
			StringEntity param = new StringEntity(JSON.toJSONString(submitCouponOrderRequest),"utf-8");
			logger.info("creating soho3q coupon order request:"+JSON.toJSONString(submitCouponOrderRequest));
			request.setEntity(param);	
			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for creating coupon order is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for creating soho3q coupon order");					
						throw new RebatesException(Messages.FAIL_TO_CREATE_SOHO3Q_COUPON_ORDER_CODE,Messages.FAIL_TO_CREATE_SOHO3Q_COUPON_ORDER_MSG);
					}else{
						success=true;
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to create soho3q coupon order by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to create soho3q coupon order",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to create soho3q coupon order",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		return success;
		
	}

}
