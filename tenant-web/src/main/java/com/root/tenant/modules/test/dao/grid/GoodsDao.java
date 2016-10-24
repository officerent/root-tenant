/**
 * Copyright &copy; 2015-2020 <a href="http://www.rootcloud.com/">rootcloud</a> All rights reserved.
 */
package com.root.tenant.modules.test.dao.grid;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.test.entity.grid.Category;
import com.root.tenant.modules.test.entity.grid.Goods;

import java.util.List;

/**
 * 商品DAO接口
 * @author liugf
 * @version 2016-10-04
 */
@MyBatisDao
public interface GoodsDao extends CrudDao<Goods> {

	public List<Category> findListBycategory(Category category);
	
}