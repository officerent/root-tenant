package com.office.rebates.dal.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.office.rebates.model.request.SohoMemberInfo;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Soho3qMemberApi {
    static Logger logger = Logger.getLogger(Soho3qMemberApi.class);

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

    public SohoMemberInfo getMemberInfo(String mobile) {
        SohoMemberInfo member = new SohoMemberInfo();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpGet request = new HttpGet(url+"?mobile="+mobile);
        RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        //HttpPost httpPost = new HttpPost(props.get("sms.url"));
        request.setConfig(config);
        CloseableHttpResponse response = null;
        try {
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //nameValuePairs.add(new BasicNameValuePair("mobile", mobile));
            //request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            response = closeableHttpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String httpResult = EntityUtils.toString(response.getEntity());
                if (httpResult != null) {
                    JSONObject json = JSON.parseObject(httpResult);
//                    logger.info("http response for getting member info is" + json);
                    if("0".equals(json.getString("code"))){
                    	Boolean isMember=json.getBoolean("result");
                    	member.setIsMember(isMember);
                    	//member = JSONObject.parseObject(json.getString("result"), SohoMemberInfo.class);
                    }
                    
                } else {
                    logger.error("http response body is null!");
                }
            } else {
                logger.error("fail to get soho3q member info by http request:" + response.getStatusLine().getStatusCode());
            }
        } catch (ClientProtocolException e) {
            logger.error("fail to call soho3q to get member info", e);
        } catch (IOException e) {
            logger.error("fail to call soho3q to get member info", e);
        } finally {
            try {
                if (response != null) response.close();
                closeableHttpClient.close();
            } catch (IOException e) {
                logger.error("fail to close http client", e);
            }
        }
        return member;
    }
}
