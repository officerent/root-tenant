package com.office.rebates.dal.dao;

import java.util.List;

import com.office.rebates.model.BonusModel;

public interface NewRebatesBonusMapper {

	public List<BonusModel> getRecentBonusList();
}