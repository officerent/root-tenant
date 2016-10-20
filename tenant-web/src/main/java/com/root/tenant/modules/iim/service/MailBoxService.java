/**
 * Copyright &copy; 2015-2020 <a href="http://www.wusyx.com/">wusyx</a> All rights reserved.
 */
package com.root.tenant.modules.iim.service;

import com.root.tenant.common.persistence.Page;
import com.root.tenant.common.service.CrudService;
import com.root.tenant.modules.iim.dao.MailBoxDao;
import com.root.tenant.modules.iim.entity.MailBox;
import com.root.tenant.modules.iim.entity.MailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收件箱Service
 * @author tenant
 * @version 2015-11-13
 */
@Service
@Transactional(readOnly = true)
public class MailBoxService extends CrudService<MailBoxDao, MailBox> {

	@Autowired
	private MailBoxDao mailBoxDao;
	public MailBox get(String id) {
		return super.get(id);
	}
	
	public List<MailBox> findList(MailBox mailBox) {
		return super.findList(mailBox);
	}
	
	public Page<MailBox> findPage(MailPage<MailBox> page, MailBox mailBox) {
		return super.findPage(page, mailBox);
	}
	
	@Transactional(readOnly = false)
	public void save(MailBox mailBox) {
		super.save(mailBox);
	}
	
	@Transactional(readOnly = false)
	public void delete(MailBox mailBox) {
		super.delete(mailBox);
	}
	
	public int getCount(MailBox mailBox) {
		return mailBoxDao.getCount(mailBox);
	}
	
}