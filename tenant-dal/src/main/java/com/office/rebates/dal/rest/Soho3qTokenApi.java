package com.office.rebates.dal.rest;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.util.Base64;
import com.office.rebates.util.SHA1Util;


public class Soho3qTokenApi {
	@Autowired
	private Soho3qCodeApi soho3qCodeApi;
	
	static Logger logger = Logger.getLogger(Soho3qTokenApi.class);	
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
	
	public String getToken(String account,String password) throws RebatesException {
		String token=null;
		String code=soho3qCodeApi.getCode();
		String url2=url+"&code="+code+"&account="+account+"&password="+encrypt(password);
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpGet request = new HttpGet(url2);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();		
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
	 //HttpPost httpPost = new HttpPost(props.get("sms.url")); 
		request.setConfig(config);	 
		CloseableHttpResponse response=null;	
		try {
			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for getting user token is"+json);
					if("N".equals(json.getString("status"))){
						logger.error("soho3q return bad response");
						String errCode=json.getString("errorCode");						
						if("10003".equals(errCode)){//密码错误
							throw new RebatesException(Messages.SALES_INVALID_PASSWORD_CODE,Messages.SALES_INVALID_PASSWORD_MSG);
						}else if("10009".equals(errCode)){//密码错误账户冻结
							throw new RebatesException(Messages.SALES_FROZEN_CODE,Messages.SALES_FROZEN_MSG);
						}else{  //其他错误
							throw new RebatesException(Messages.FAIL_TO_GET_TOKEN_CODE,Messages.FAIL_TO_GET_TOKEN_MSG);
						}
					}else{
						token=json.getJSONObject("results").getString("access_token");
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to get soho3q token by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to get token",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to get token",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		logger.info("got user token:"+token);
		return token;
	}
	
	private static String encrypt(String password) {
		byte[] bytes = SHA1Util.SHA1(password);
		String base64password = Base64.encodeBytes(bytes);
		return URLEncoder.encode(base64password);
	}
}
