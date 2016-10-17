package com.office.rebates.dal.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.office.rebates.model.Soho3qUserLoginResult;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;

public class Soho3qUserLoginApi {
	
	static Logger logger = Logger.getLogger(Soho3qUserLoginApi.class);	
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
	
	public Soho3qUserLoginResult login(String userName,String password) throws RebatesException {
		Soho3qUserLoginResult loginResult = new Soho3qUserLoginResult();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpPost request = new HttpPost(url);
		CookieStore httpCookieStore = new BasicCookieStore();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();		
		CloseableHttpClient closeableHttpClient = httpClientBuilder.setDefaultCookieStore(httpCookieStore).build();  
		request.setConfig(config);	 
		CloseableHttpResponse response=null;	
		try {
			request.addHeader("content-type", "application/x-www-form-urlencoded");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
			nameValuePairs.add(new BasicNameValuePair("account", userName));
			nameValuePairs.add(new BasicNameValuePair("pwd", password));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  

			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for soho3q  user log in is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for soho3q  user log in");					
						throw new RebatesException(Messages.FAIL_TO_LOGIN_SOHO3Q_CODE,Messages.FAIL_TO_LOGIN_SOHO3Q_MSG);
					}else{
						//logger.info("headers is:"+JSON.toJSONString(response.getAllHeaders()));
						//Header[] headers = response.getHeaders("Set-Cookie");
						//logger.info("headers is:"+JSON.toJSONString(headers));
//						for(int i=0;i<headers.length;i++){
//							Header header=headers[i];
//							if("token".equals(header.getName())){
//								loginResult.setToken(header.getValue());
//							}
//							
//							if("sid".equals(header.getName())){
//								loginResult.setSid(header.getValue());
//							}
//						}
						List<Cookie> cookies=httpCookieStore.getCookies();
						logger.info("cookies is:"+JSON.toJSONString(cookies));
						if(cookies!=null && cookies.size()>0){
							for (int i = 0; i < cookies.size(); i++) {
								if (Constants.SOHO3Q_USER_TOKEN.equalsIgnoreCase(cookies.get(i).getName())) {
									loginResult.setToken(cookies.get(i).getValue());
								}
								if (Constants.SOHO3Q_SID.equalsIgnoreCase(cookies.get(i).getName())) {
									loginResult.setSid(cookies.get(i).getValue());
								}
							}
							
						}
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to soho3q  user log in by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to soho3q  user log in",e);
		} catch (IOException e) {
			logger.error("fail to soho3q  user log in",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		logger.info("user login info for user:"+userName+" is:"+JSON.toJSONString(loginResult));
		return loginResult;
	}
	
}
