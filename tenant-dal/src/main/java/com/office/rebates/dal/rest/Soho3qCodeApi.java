package com.office.rebates.dal.rest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Soho3qCodeApi {
	static Logger logger = Logger.getLogger(Soho3qCodeApi.class);	
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
	
	public String getCode() {
		String code=null;
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpGet request = new HttpGet(url);
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
					logger.info("http response for getting code is"+json);
					if("N".equals(json.getString("status"))){
						logger.error("soho3q return bad response");
					}else{
						code=json.getString("code");
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to get soho3q code by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to get code",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to get code",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		return code;
	}
}
