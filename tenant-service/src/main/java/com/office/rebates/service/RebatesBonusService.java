package com.office.rebates.service;

import java.util.List;

import com.office.rebates.model.BonusModel;
import com.office.rebates.model.common.RebatesException;

public interface RebatesBonusService {

	List<BonusModel> getRecentBonus() throws RebatesException;

	
}
