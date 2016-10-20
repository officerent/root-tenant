/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.test.dao.tree;

import com.root.tenant.common.persistence.TreeDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.test.entity.tree.TestTree;

/**
 * 组织机构DAO接口
 * @author liugf
 * @version 2016-10-04
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}