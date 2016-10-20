/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.test.dao.validation;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.test.entity.validation.TestValidation;

/**
 * 测试校验功能DAO接口
 * @author lgf
 * @version 2016-10-05
 */
@MyBatisDao
public interface TestValidationDao extends CrudDao<TestValidation> {

	
}