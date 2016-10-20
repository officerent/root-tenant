/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.test.dao.onetomany;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.test.entity.onetomany.TestDataChild;

/**
 * 票务代理DAO接口
 * @author liugf
 * @version 2016-10-06
 */
@MyBatisDao
public interface TestDataChildDao extends CrudDao<TestDataChild> {

	
}