package com.office.rebates.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.office.rebates.model.Soho3qProductModel;
import com.office.rebates.model.Soho3qProjectModel;
import com.office.rebates.model.UserInfo;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.common.ResultCode;
import com.office.rebates.model.request.CreateOrderRequest;
import com.office.rebates.service.RebatesOrderService;
import com.office.rebates.service.Soho3qAccessService;
import com.office.rebates.service.UserService;


@Controller
@RequestMapping(RouteKey.PREFIX_AJAX+RouteKey.SOHO3Q)
public class Soho3qAccessController {
    @Autowired
    private Soho3qAccessService soho3qAccessService;
    
    static Logger logger = LoggerFactory.getLogger(Soho3qAccessController.class);
	
    //获取项目列表
    @ResponseBody
	@RequestMapping(value = RouteKey.PROJECT_LIST, method = RequestMethod.GET)
	public ResultCode<List<Soho3qProjectModel>> getProjectList() {
		ResultCode<List<Soho3qProjectModel>> result=new ResultCode<List<Soho3qProjectModel>>();		

		try {
			List<Soho3qProjectModel> projectList=soho3qAccessService.getProjectList();
			result.setData(projectList);
		} catch (RebatesException e) {
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}
		return result;

	}
    
    //获取产品列表
    @ResponseBody
	@RequestMapping(value = RouteKey.PRODUCT_LIST, method = RequestMethod.GET)
	public ResultCode<List<Soho3qProductModel>> getProductList(String projectId,String checkInDate,String checkOutDate) {
		ResultCode<List<Soho3qProductModel>> result=new ResultCode<List<Soho3qProductModel>>();		
		try {
			List<Soho3qProductModel> productList=soho3qAccessService.getProductList(projectId,checkInDate,checkOutDate);
			result.setData(productList);
		} catch (RebatesException e) {
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}
		return result;

	}
}
