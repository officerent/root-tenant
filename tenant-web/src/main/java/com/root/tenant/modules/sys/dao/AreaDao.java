/**
 * Copyright &copy; 2015-2020 <a href="http://www.rootcloud.com/">rootcloud</a> All rights reserved.
 */
package com.root.tenant.modules.sys.dao;

import com.root.tenant.common.persistence.TreeDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author tenant
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
