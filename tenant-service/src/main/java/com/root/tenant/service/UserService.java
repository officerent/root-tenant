package com.root.tenant.service;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;

import com.root.tenant.dal.dataobj.User;
import com.root.tenant.model.common.TenantException;

//用户服务
public interface UserService {
	
	//注册用户
	void registerUser(String userName,String email,String password,String verificationCode, HttpServletRequest request) throws TenantException;
	
	//获取图片验证码
	BufferedImage getVerificationImage(HttpServletRequest request);
	
	//新增一个用户
		void  addUser(User user) throws TenantException;

}
