package com.office.rebates.dal.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.office.rebates.model.CouponModel;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;

public class Soho3qGetCouponListApi {
	
	static Logger logger = Logger.getLogger(Soho3qGetCouponListApi.class);	
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
	
	public List<CouponModel> getCouponList() throws RebatesException {
		List<CouponModel> couponList = new ArrayList<CouponModel>();
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
					logger.info("http response for getting coupon List is "+json);
					if(!"0".equals(json.getString("code"))){
						logger.error("soho3q return bad response for getting coupon List");					
						throw new RebatesException(Messages.FAIL_TO_GET_COUPON_LIST_CODE,Messages.FAIL_TO_GET_COUPON_LIST_MSG);
					}else{
						JSONArray listData=json.getJSONArray("result");
						if(listData!=null){
							for(int i=0;i<listData.size();i++){
								CouponModel coupon=JSON.parseObject(listData.getString(i),CouponModel.class);
								if(Constants.CONPON_TYPE_MEMBERSHIP.equals(coupon.getCouponType())){
									coupon.setDescription(Constants.DESCRIPTION_MEMBERSHIP);
								}else if(Constants.CONPON_TYPE_STANDARD.equals(coupon.getCouponType())){
									if(Constants.PRODUCT_TYPE_OPEN_STATION.equals(coupon.getProductType())){
										coupon.setDescription(Constants.DESCRIPTION_OPEN_STATION);
									}else if(Constants.PRODUCT_TYPE_ROOM.equals(coupon.getProductType())){
										coupon.setDescription(Constants.DESCRIPTION_ROOM);
									}
								}
								
								if("*".equals(coupon.getCityId())){
									coupon.setCityName("全国通用");
								}
								
								if("*".equals(coupon.getProjectId())){
									coupon.setProjectName("所有SOHO3Q通用");
								}
								//logger.info("got sales order :"+JSON.toJSONString(soho3qOrder));
								couponList.add(coupon);
							}
						}
						
					}					
				} else {
					logger.error("http response body is null!");
				}
			} else {
				logger.error("fail to get coupon List by http request:"+ response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("fail to call soho3q to get coupon List",e);
		} catch (IOException e) {
			logger.error("fail to call soho3q to get coupon List",e);
		}finally{
			try {
				if(response!=null) response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				logger.error("fail to close http client",e);
			}
		}
		return couponList;
	}
	
}
