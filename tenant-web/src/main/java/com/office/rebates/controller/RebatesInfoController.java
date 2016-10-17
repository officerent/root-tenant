package com.office.rebates.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.office.rebates.model.RebatesRatio;
import com.office.rebates.model.UserInfo;
import com.office.rebates.model.UserLoginInfo;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.common.ResultCode;
import com.office.rebates.model.request.CreateOrderRequest;
import com.office.rebates.service.RebatesInfoService;
import com.office.rebates.service.RebatesOrderService;
import com.office.rebates.service.UserService;
import com.office.rebates.util.CookieHelper;
import com.office.rebates.util.PropertiesUtils;


@Controller
@RequestMapping(RouteKey.PREFIX_AJAX+"/"+RouteKey.REBATES_INFO)
public class RebatesInfoController {
    @Autowired
    private RebatesInfoService rebatesInfoService;
    
    
    static Logger logger = LoggerFactory.getLogger(RebatesInfoController.class);
	
    //获取返利率
    @ResponseBody
	@RequestMapping(value = RouteKey.REBATES_RATIO, method = RequestMethod.GET)
	public ResultCode<RebatesRatio> getRebatesRatio() {
    	ResultCode<RebatesRatio> result=new ResultCode<RebatesRatio>();		
		
		//create the user
		try {
			RebatesRatio ratio=rebatesInfoService.getRebatesRatio();
			result.setData(ratio);
		} catch (RebatesException e) {
			logger.error("fail to get rebates ratio",e);
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}catch (Exception e) {
			logger.error("fail to get rebates ratio for unexpected reason",e);
			result.setErrCode(Messages.UPEXPECTED_ERROR_CODE);
			result.setErrMsg(e.getMessage());
		}
		return result;

	}
    
}
