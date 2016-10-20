/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.test.entity.grid;


import com.root.tenant.common.persistence.DataEntity;
import com.root.tenant.common.utils.excel.annotation.ExcelField;

/**
 * 商品分类Entity
 * @author liugf
 * @version 2016-10-04
 */
public class Category extends DataEntity<Category> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 类型名
	
	public Category() {
		super();
	}

	public Category(String id){
		super(id);
	}

	@ExcelField(title="类型名", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}