package com.root.tenant.service;

import com.root.tenant.dal.dataobj.User;
import com.root.tenant.model.common.TenantException;

//用户服务
public interface UserService {
	
	//新增一个用户
	void  addUser(User user) throws TenantException;

}
