package com.office.rebates.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.office.rebates.model.RebatesRatio;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.service.RebatesInfoService;
import com.office.rebates.util.PropertiesUtils;

@Service
public class RebatesInfoServiceImpl implements RebatesInfoService{

	static Logger logger = LoggerFactory.getLogger(RebatesInfoServiceImpl.class);

	@Override
	public RebatesRatio getRebatesRatio() throws RebatesException {
		String ratio=PropertiesUtils.prop.get("rebate_ratio")+"%";
		RebatesRatio rebatesRatio=new RebatesRatio();
		rebatesRatio.setRatio(ratio);
		return rebatesRatio;
	}

	
	
}
