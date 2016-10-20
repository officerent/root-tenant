/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.monitor.dao;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.monitor.entity.Monitor;

/**
 * 系统监控DAO接口
 * @author liugf
 * @version 2016-02-07
 */
@MyBatisDao
public interface MonitorDao extends CrudDao<Monitor> {
	
}