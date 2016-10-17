package com.office.rebates.service;

import java.util.List;

import javax.servlet.http.Cookie;

import com.office.rebates.model.UserInfo;
import com.office.rebates.model.UserLoginInfo;
import com.office.rebates.model.common.RebatesException;

public interface UserService {
	
	//根据usertoken获取用户信息
	UserInfo  getUserInfo(String token);

	UserInfo getUserInfo(Cookie[] cookies);

	UserLoginInfo registerUser(String userName, String password) throws RebatesException,Exception;
	
	UserLoginInfo login(String userName, String password) throws RebatesException,Exception;
}
