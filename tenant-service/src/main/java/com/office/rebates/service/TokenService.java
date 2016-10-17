package com.office.rebates.service;

import java.util.List;

import com.office.rebates.model.UserInfo;

public interface TokenService {
	
	
	//产生user token,保存到redis
	public String createToken(UserInfo userInfo);
		
	//根据token从redis获取userinfo
	public UserInfo getUserInfoByToken(String token);
}
