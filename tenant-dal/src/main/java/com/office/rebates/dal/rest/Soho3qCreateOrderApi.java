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
import com.office.rebates.model.request.CreateOrderRequest;
import com.office.rebates.model.request.SelectProductModel;
import com.office.rebates.model.request.SelectProductRequest;
import com.office.rebates.model.request.Soho3qOrderProdSummary;
import com.office.rebates.model.request.SubmitOrderRequest;
import com.office.rebates.model.OrderItem;



public class Soho3qCreateOrderApi {
	//@Autowired
	//private Soho3qTokenApi soho3qTokenApi;
	
	@Autowired
	private Soho3qCheckOrderApi soho3qCheckOrderApi;
	
	@Autowired
	private Soho3qUserLoginApi soho3qUserLoginApi;
	
	static Logger logger = Logger.getLogger(Soho3qCreateOrderApi.class);	
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
	
	public Long createSoho3qOrder(CreateOrderRequest request,SalesPeople people) throws RebatesException {
		logger.info("creating order by request:"+JSON.toJSONString(request)+" by sales people:"+JSON.toJSONString(people));
		
		//创建token
		//String token=soho3qTokenApi.getToken(people.getUserName(), people.getUserPassword());
		
		Soho3qUserLoginResult login=soho3qUserLoginApi.login(people.getUserName(), people.getUserPassword());
		logger.info("got login info:"+JSON.toJSONString(login));
		
		//获得key
		SelectProductRequest productRequest=new SelectProductRequest();
		productRequest.setEndDate(request.getCheckOutDate());
		ArrayList<SelectProductModel> productList=new ArrayList<SelectProductModel>();
		if(request.getOrderItems()!=null&&!request.getOrderItems().isEmpty()){
			for(OrderItem orderItem:request.getOrderItems()){
				SelectProductModel productModel=new SelectProductModel();
				productModel.setBookNum(orderItem.getBookNum());
				productModel.setProductSubType(orderItem.getProductSubType());
				productModel.setProductType(orderItem.getProductType());
				productList.add(productModel);
			}
		}
		productRequest.setProductList(productList);
		productRequest.setProjectId(request.getProjectId());
		productRequest.setSource("2");
		productRequest.setStartDate(request.getCheckInDate());
		Integer weekCount=0;
		if(request.getPeriodMonth()!=null){
			weekCount=request.getPeriodMonth()*4+request.getPeriodWeek();
		}else{
			weekCount=request.getPeriodWeek();
		}
		productRequest.setWeekCount(weekCount);
		String key=getOrderProductKey(productRequest);
		logger.info("got product list key:"+key);
		if(key==null){
			logger.error("key is null");
			throw new RebatesException(Messages.FAIL_TO_GET_ORDER_KEY_CODE,Messages.FAIL_TO_GET_ORDER_KEY_MSG);
		}
		
		//获得workbench ids
		Soho3qOrderProdSummary prodSummary=getOrderProductSummary(key,login.getToken(),login.getSid());
		logger.info("got product summary:"+JSON.toJSONString(prodSummary));
		if(prodSummary==null){
			logger.error("order product summary is null");
			throw new RebatesException(Messages.FAIL_TO_GET_ORDER_PRODUCT_SUMMARY_CODE,Messages.FAIL_TO_GET_ORDER_PRODUCT_SUMMARY_MSG);
		}
		
		//下单
		SubmitOrderRequest submitOrderRequest=new SubmitOrderRequest();
		submitOrderRequest.setCompany(request.getCustomerCompany());
		submitOrderRequest.setCustomer(request.getCustomerName());
		submitOrderRequest.setEmail("");
		submitOrderRequest.setEndDate(prodSummary.getCheckOutDate());
		submitOrderRequest.setPhone(request.getCustomerMobile());
		submitOrderRequest.setProdInfoKey(key);
		submitOrderRequest.setStartDate(prodSummary.getCheckInDate());
		submitOrderRequest.setWorkbenchId(prodSummary.getWorkbenchIds());
		boolean success=createSoho3qOrder(login.getToken(),login.getSid(),submitOrderRequest);
		if(!success){
			logger.error("failt to create soho3q order");
			throw new RebatesException(Messages.FAIL_TO_CREATE_SOHO3Q_ORDER_CODE,Messages.FAIL_TO_CREATE_SOHO3Q_ORDER_MSG);
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
	
	private boolean createSoho3qOrder(String token,String sid, SubmitOrderRequest submitOrderRequest) throws RebatesException {
		boolean success=false;
		String submitOrderUrl="http://m.soho3q.com/salesvc/ajax/booking/submitcustomorder";
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpPost request = new HttpPost(submitOrderUrl);
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
			StringEntity param = new StringEntity(JSON.toJSONString(submitOrderRequest),"utf-8");
			logger.info("creating soho3q order request:"+JSON.toJSONString(submitOrderRequest));
			request.setEntity(param);	
			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for creating order is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for creating soho3q order");					
						throw new RebatesException(Messages.FAIL_TO_CREATE_SOHO3Q_ORDER_CODE,Messages.FAIL_TO_CREATE_SOHO3Q_ORDER_MSG);
					}else{
						success=true;
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to create soho3q order by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to create soho3q order",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to create soho3q order",e);
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
	//获取order key
	private String getOrderProductKey(SelectProductRequest productRequest) throws RebatesException {
		String key=null;
		String keyUrl="http://m.soho3q.com/salesvc/ajax/booking/saveorder";
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpPost request = new HttpPost(keyUrl);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();		
		
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
		request.setConfig(config);	 
		CloseableHttpResponse response=null;	
		try {
			request.addHeader("content-type", "application/json");
			StringEntity param = new StringEntity(JSON.toJSONString(productRequest));
			request.setEntity(param);	
			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for getting order product key is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for get order product key");					
						throw new RebatesException(Messages.FAIL_TO_GET_ORDER_KEY_CODE,Messages.FAIL_TO_GET_ORDER_KEY_MSG);
					}else{
						key=json.getString("result");
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to get soho3q order key by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to get order key",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to get order key",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		return key;
	}
	
	//获取product summary
	private Soho3qOrderProdSummary getOrderProductSummary(String key,String token,String sid) throws RebatesException {
		Soho3qOrderProdSummary orderSummary=null;
		String productSummaryUrl="http://m.soho3q.com/salesvc/ajax/booking/getorderproduct";
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpPost request = new HttpPost(productSummaryUrl);
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
			request.addHeader("content-type", "application/x-www-form-urlencoded");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
			nameValuePairs.add(new BasicNameValuePair("key", key));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8")); 
			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for getting product summary is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for get order product summary");					
						throw new RebatesException(Messages.FAIL_TO_GET_ORDER_KEY_CODE,Messages.FAIL_TO_GET_ORDER_KEY_MSG);
					}else{
						orderSummary=JSON.parseObject(json.getJSONObject("result").toString(), Soho3qOrderProdSummary.class);
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to get soho3q order product summary by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to get order product summary",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to get order product summary",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		return orderSummary;
	}
}
