/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.test.entity.note;


import com.root.tenant.common.persistence.DataEntity;
import com.root.tenant.common.utils.excel.annotation.ExcelField;

/**
 * 富文本测试Entity
 * @author liugf
 * @version 2016-10-04
 */
public class TestNote extends DataEntity<TestNote> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String contents;		// 内容
	
	public TestNote() {
		super();
	}

	public TestNote(String id){
		super(id);
	}

	@ExcelField(title="标题", align=2, sort=7)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ExcelField(title="内容", align=2, sort=8)
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
}