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
import org.apache.http.client.methods.HttpGet;
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
import com.office.rebates.model.Soho3qProductModel;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;

public class Soho3qGetProductListApi {
	
	static Logger logger = Logger.getLogger(Soho3qGetProductListApi.class);	
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
	
	public List<Soho3qProductModel> getProductList(String projectId, String checkInDate, String checkOutDate) throws RebatesException {
		List<Soho3qProductModel> productList = new ArrayList<Soho3qProductModel>();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpPost request = new HttpPost(url);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();		
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
		request.setConfig(config);	 
		CloseableHttpResponse response=null;	
		try { 			
			request.addHeader("content-type", "application/x-www-form-urlencoded");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); 
			nameValuePairs.add(new BasicNameValuePair("projectId", projectId));
			nameValuePairs.add(new BasicNameValuePair("checkInDate", checkInDate));
			nameValuePairs.add(new BasicNameValuePair("checkOutDate",checkOutDate));
			nameValuePairs.add(new BasicNameValuePair("source", "0"));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  			
			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for getting Product List is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for getting Product List");					
						throw new RebatesException(Messages.FAIL_TO_GET_SOHO3Q_PRODUCT_LIST_CODE,Messages.FAIL_TO_GET_SOHO3Q_PRODUCT_LIST_MSG);
					}else{
						JSONArray listData=json.getJSONArray("result");
						if(listData!=null){
							for(int i=0;i<listData.size();i++){
								Soho3qProductModel Product=JSON.parseObject(listData.getString(i),Soho3qProductModel.class);
								//logger.info("got sales order :"+JSON.toJSONString(soho3qOrder));
								productList.add(Product);
							}
						}
						
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to get Product List by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to get Product List",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to get Product List",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		return productList;
	}
	
}
