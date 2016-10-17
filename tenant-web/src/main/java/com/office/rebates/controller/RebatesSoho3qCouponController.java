package com.office.rebates.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.office.rebates.model.CouponModel;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.common.ResultCode;
import com.office.rebates.service.Soho3qCouponService;




@Controller
@RequestMapping(RouteKey.PREFIX_AJAX+"/"+RouteKey.SOHO3Q)
public class RebatesSoho3qCouponController {
    @Autowired
    private Soho3qCouponService soho3qCouponService;
    
    
    static Logger logger = LoggerFactory.getLogger(RebatesSoho3qCouponController.class);
	
    @ResponseBody
	@RequestMapping(value = RouteKey.COUPON_LIST, method = RequestMethod.GET)
	public ResultCode<List<CouponModel>> getCouponList() {
    	//logger.info("registering user with name:"+userName+",password:"+password);
    	ResultCode<List<CouponModel>> result=new ResultCode<List<CouponModel>>();		
		
		try {
			List<CouponModel> couponList=soho3qCouponService.getCouponList();
			result.setData(couponList);
		} catch (RebatesException e) {
			logger.error("fail to get coupon list",e);
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}catch (Exception e) {
			logger.error("fail to get coupon list for unexpected reason",e);
			result.setErrCode(Messages.UPEXPECTED_ERROR_CODE);
			result.setErrMsg(e.getMessage());
		}
		return result;

	}
    

}
