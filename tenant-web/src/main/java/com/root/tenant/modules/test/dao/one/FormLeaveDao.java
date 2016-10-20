/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.test.dao.one;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.test.entity.one.FormLeave;

/**
 * 请假表单DAO接口
 * @author lgf
 * @version 2016-10-06
 */
@MyBatisDao
public interface FormLeaveDao extends CrudDao<FormLeave> {

	
}