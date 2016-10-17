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

public class Soho3qCheckOrderApi {
	@Autowired
	private Soho3qUserLoginApi soho3qUserLoginApi;
	
	static Logger logger = Logger.getLogger(Soho3qCheckOrderApi.class);	
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
	
	public List<Soho3qOrder> getSoho3qOrderBySales(SalesPeople people,Integer pageNum,Integer pageSize) throws RebatesException {
		//List<Soho3qOrder> soho3qOrders = new ArrayList<Soho3qOrder>();
		Soho3qUserLoginResult login=soho3qUserLoginApi.login(people.getUserName(), people.getUserPassword());
		logger.info("got login info:"+JSON.toJSONString(login));
		List<Soho3qOrder> soho3qOrders=getSoho3qOrderByToken(login.getToken(),login.getSid(),pageNum,pageSize);
		return soho3qOrders;
	}
	
	private List<Soho3qOrder> getSoho3qOrderByToken(String token,String sid,Integer pageNum,Integer pageSize) throws RebatesException {
		List<Soho3qOrder> soho3qOrders = new ArrayList<Soho3qOrder>();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpPost request = new HttpPost(url);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();		
		//CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
	 //HttpPost httpPost = new HttpPost(props.get("sms.url")); 
		//String token=soho3qTokenApi.getToken(people.getUserName(), people.getUserPassword());
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
			nameValuePairs.add(new BasicNameValuePair("pageSize", pageSize.toString()));
			nameValuePairs.add(new BasicNameValuePair("pageNum", pageNum.toString()));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  			
			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for getting orders is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for get orders by sales people");					
						throw new RebatesException(Messages.FAIL_TO_GET_SOHO3Q_ORDER_CODE,Messages.FAIL_TO_GET_SOHO3Q_ORDER_MSG);
					}else{
						JSONObject resultJson=json.getJSONObject("result");
						if(resultJson!=null){
							JSONArray listData=resultJson.getJSONArray("listData");
							for(int i=0;i<listData.size();i++){
								Soho3qOrder soho3qOrder=JSON.parseObject(listData.getString(i),Soho3qOrder.class);
								logger.info("got sales order :"+JSON.toJSONString(soho3qOrder));
								soho3qOrders.add(soho3qOrder);
							}
						}
						
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to get soho3q orders by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to get orders",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to get orders",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		return soho3qOrders;
	}
	
	//获取该用户最近n次订单
	public List<Soho3qOrder> getMostRecentSoho3qOrders(String token,String sid, Integer n) throws RebatesException {
		List<Soho3qOrder> soho3qOrders=getSoho3qOrderByToken(token,sid,1,n);
		logger.info("most recent "+n+" soho3q orders are:"+JSON.toJSONString(soho3qOrders));
		return soho3qOrders;
		
	}
}
