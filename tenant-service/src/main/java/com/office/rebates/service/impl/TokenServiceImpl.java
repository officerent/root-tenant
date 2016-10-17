package com.office.rebates.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.office.rebates.dal.cache.CacheClient;
import com.office.rebates.model.UserInfo;
import com.office.rebates.service.TokenService;
import com.office.rebates.util.Md5Encrypt;
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    @Qualifier("simpleRedisClient")
    private CacheClient cacheClient;

    static Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    
	@Override
	public String createToken(UserInfo userInfo) {
		logger.info("creating user token with userInfo:"+JSON.toJSONString(userInfo));
		while(true){
			Date p1=new Date();
			String part1=Md5Encrypt.md5(userInfo.getUserId()+p1.toString());			
			Date p2=new Date();
			String part2=Md5Encrypt.md5(userInfo.getName()+p2.toString());
			String token=part1+part2;
			if(cacheClient.get(token, UserInfo.class)==null){//如果已经存在，则需要换一个token;不存在的话，则直接返回
				cacheClient.set(token, userInfo);
				logger.info("created and cached user token:"+token);
				return token;
			}
		}
	}

	@Override
	public UserInfo getUserInfoByToken(String token) {
		UserInfo userInfo=cacheClient.get(token, UserInfo.class);
		return userInfo;
	}


}
