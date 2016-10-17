package com.office.rebates.service;

import com.office.rebates.model.RebatesRatio;
import com.office.rebates.model.common.RebatesException;

public interface RebatesInfoService {

	RebatesRatio getRebatesRatio() throws RebatesException;

	
}
