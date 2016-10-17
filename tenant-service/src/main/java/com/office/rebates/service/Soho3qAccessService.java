package com.office.rebates.service;

import java.util.List;

import com.office.rebates.model.Soho3qProductModel;
import com.office.rebates.model.Soho3qProjectModel;
import com.office.rebates.model.common.RebatesException;

public interface Soho3qAccessService {

	List<Soho3qProjectModel> getProjectList() throws RebatesException;

	List<Soho3qProductModel> getProductList(String projectId, String checkInDate, String checkOutDate) throws RebatesException;
	
}
