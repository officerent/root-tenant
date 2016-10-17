package com.office.rebates.dal.dao;

import com.office.rebates.dal.dataobj.RebatesGlobalLock;

public interface NewRebatesGlobalLockMapper {
	/**
	 * lock resource by select for update
	 * 
	 * @param resource name
	 * @return
	 */
	public RebatesGlobalLock lockResource(String name);
}