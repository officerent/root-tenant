package com.office.rebates.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.rebates.dal.rest.Soho3qGetCouponListApi;
import com.office.rebates.dal.rest.Soho3qGetProductListApi;
import com.office.rebates.dal.rest.Soho3qGetProjectListApi;
import com.office.rebates.model.CouponModel;
import com.office.rebates.model.Soho3qProductModel;
import com.office.rebates.model.Soho3qProjectModel;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.service.Soho3qAccessService;
import com.office.rebates.service.Soho3qCouponService;

@Service
public class Soho3qCouponServiceImpl implements Soho3qCouponService{

	static Logger logger = LoggerFactory.getLogger(Soho3qCouponServiceImpl.class);
    
	@Autowired
	private Soho3qGetCouponListApi soho3qGetCouponListApi;

	@Override
	public List<CouponModel> getCouponList() throws RebatesException {
		return soho3qGetCouponListApi.getCouponList();
	}
	

	
	
}
