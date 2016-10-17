package com.office.rebates.dal.dao;

import com.office.rebates.dal.dataobj.SalesPeople;

public interface NewSalesPeopleMapper {
	/**
	 * lock resource by select for update
	 * 
	 * @param resource name
	 * @return
	 */
	public SalesPeople getAndLockSalesPeople(Long salesId);
}