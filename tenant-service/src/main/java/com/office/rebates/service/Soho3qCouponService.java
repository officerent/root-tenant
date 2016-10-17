package com.office.rebates.service;

import java.util.List;

import com.office.rebates.model.CouponModel;
import com.office.rebates.model.common.RebatesException;

public interface Soho3qCouponService {

	List<CouponModel> getCouponList() throws RebatesException;

	
	
}
