/**
 * Copyright &copy; 2015-2020 <a href="http://www.rootcloud.com/">rootcloud</a> All rights reserved.
 */
package com.root.tenant.modules.tools.dao;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.tools.entity.TestInterface;

/**
 * 接口DAO接口
 * @author lgf
 * @version 2016-01-07
 */
@MyBatisDao
public interface TestInterfaceDao extends CrudDao<TestInterface> {
	
}