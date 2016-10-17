package com.office.rebates.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.rebates.dal.rest.Soho3qGetProductListApi;
import com.office.rebates.dal.rest.Soho3qGetProjectListApi;
import com.office.rebates.model.Soho3qProductModel;
import com.office.rebates.model.Soho3qProjectModel;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.service.Soho3qAccessService;

@Service
public class Soho3qAccessServiceImpl implements Soho3qAccessService{

	static Logger logger = LoggerFactory.getLogger(Soho3qAccessServiceImpl.class);
    
	@Autowired
	private Soho3qGetProjectListApi soho3qGetProjectListApi;
	
	@Autowired
	private Soho3qGetProductListApi soho3qGetProductListApi;

	@Override
	public List<Soho3qProjectModel> getProjectList() throws RebatesException {	
		return soho3qGetProjectListApi.getProjectList();
	}

	@Override
	public List<Soho3qProductModel> getProductList(String projectId, String checkInDate, String checkOutDate)
			throws RebatesException {
		List<Soho3qProductModel> soho3qProductModels = soho3qGetProductListApi.getProductList(projectId, checkInDate, checkOutDate);
		for(Soho3qProductModel soho3qProductModel:soho3qProductModels){
			soho3qProductModel.setTitle(soho3qProductModel.getTitle().replace("{0}","人"));
		}
		return soho3qProductModels;
	}

	
	
}
