/**
 * Copyright &copy; 2015-2020 <a href="http://www.rootcloud.com/">rootcloud</a> All rights reserved.
 */
package com.root.tenant.modules.iim.dao;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.iim.entity.LayGroup;

/**
 * 群组DAO接口
 * @author lgf
 * @version 2016-08-07
 */
@MyBatisDao
public interface LayGroupDao extends CrudDao<LayGroup> {

	
}