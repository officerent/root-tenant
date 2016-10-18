package com.root.tenant.service.impl;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.root.tenant.dal.dao.UserMapper;
import com.root.tenant.dal.dataobj.User;
import com.root.tenant.model.common.Constants;
import com.root.tenant.model.common.TenantException;
import com.root.tenant.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    
	@Override
	public void addUser(User user) throws TenantException {
		user.setStatus(Constants.USER_STATUS_NORMAL);
		Date now=new Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		userMapper.insert(user);
		logger.info("new user was inserted:"+JSON.toJSONString(user));
		
	} 
	
	
}
