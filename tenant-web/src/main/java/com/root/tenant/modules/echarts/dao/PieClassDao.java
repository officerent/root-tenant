/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.echarts.dao;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.echarts.entity.PieClass;

/**
 * 班级DAO接口
 * @author lgf
 * @version 2016-05-26
 */
@MyBatisDao
public interface PieClassDao extends CrudDao<PieClass> {

	
}