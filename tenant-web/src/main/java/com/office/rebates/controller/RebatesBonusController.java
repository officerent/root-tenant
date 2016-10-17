package com.office.rebates.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.office.rebates.model.BonusModel;
import com.office.rebates.model.UserInfo;
import com.office.rebates.model.UserLoginInfo;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.common.ResultCode;
import com.office.rebates.service.RebatesBonusService;
import com.office.rebates.util.CookieHelper;
import com.office.rebates.util.PropertiesUtils;


@Controller
@RequestMapping(RouteKey.PREFIX_AJAX+"/"+RouteKey.BONUS)
public class RebatesBonusController {
    @Autowired
    private RebatesBonusService rebatesBonusService;
    
    
    static Logger logger = LoggerFactory.getLogger(RebatesBonusController.class);
	
    //获取返利排行榜
    @ResponseBody
	@RequestMapping(value = RouteKey.BONUS_RECENT10, method = RequestMethod.GET)
	public ResultCode<List<BonusModel>> getRebatesRatio() {
    	ResultCode<List<BonusModel>> result=new ResultCode<List<BonusModel>>();		
		
		//create the user
		try {
			List<BonusModel> bonusList=rebatesBonusService.getRecentBonus();
			result.setData(bonusList);
		} catch (RebatesException e) {
			logger.error("fail to get recent bonus List",e);
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}catch (Exception e) {
			logger.error("fail to get recent bonus List for unexpected reason",e);
			result.setErrCode(Messages.UPEXPECTED_ERROR_CODE);
			result.setErrMsg(e.getMessage());
		}
		return result;

	}
    
}
