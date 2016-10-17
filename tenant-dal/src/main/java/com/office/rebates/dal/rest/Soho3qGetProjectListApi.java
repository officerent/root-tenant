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
import com.office.rebates.model.Soho3qProjectModel;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;

public class Soho3qGetProjectListApi {
	
	static Logger logger = Logger.getLogger(Soho3qGetProjectListApi.class);	
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
	
	public List<Soho3qProjectModel> getProjectList() throws RebatesException {
		List<Soho3qProjectModel> projectList = new ArrayList<Soho3qProjectModel>();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
		HttpGet request = new HttpGet(url);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();		
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
		request.setConfig(config);	 
		CloseableHttpResponse response=null;	
		try { 			
			response = closeableHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String httpResult = EntityUtils.toString(response.getEntity());
				if (httpResult != null) {
					JSONObject json = JSON.parseObject(httpResult);
					logger.info("http response for getting project List is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for getting project List");					
						throw new RebatesException(Messages.FAIL_TO_GET_SOHO3Q_PROJECT_LIST_CODE,Messages.FAIL_TO_GET_SOHO3Q_PROJECT_LIST_MSG);
					}else{
						JSONArray listData=json.getJSONArray("result");
						if(listData!=null){
							for(int i=0;i<listData.size();i++){
								Soho3qProjectModel project=JSON.parseObject(listData.getString(i),Soho3qProjectModel.class);
								//logger.info("got sales order :"+JSON.toJSONString(soho3qOrder));
								projectList.add(project);
							}
						}
						
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to get project List by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to get project List",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to get project List",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		return projectList;
	}
	
}
