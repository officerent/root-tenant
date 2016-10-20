/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.tools.service;

import com.root.tenant.common.persistence.Page;
import com.root.tenant.common.service.CrudService;
import com.root.tenant.modules.tools.dao.TestInterfaceDao;
import com.root.tenant.modules.tools.entity.TestInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 接口Service
 * @author lgf
 * @version 2016-01-07
 */
@Service
@Transactional(readOnly = true)
public class TestInterfaceService extends CrudService<TestInterfaceDao, TestInterface> {

	public TestInterface get(String id) {
		return super.get(id);
	}
	
	public List<TestInterface> findList(TestInterface testInterface) {
		return super.findList(testInterface);
	}
	
	public Page<TestInterface> findPage(Page<TestInterface> page, TestInterface testInterface) {
		return super.findPage(page, testInterface);
	}
	
	@Transactional(readOnly = false)
	public void save(TestInterface testInterface) {
		super.save(testInterface);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestInterface testInterface) {
		super.delete(testInterface);
	}
	
}