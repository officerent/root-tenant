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
import com.office.rebates.model.UserInfo;
import com.office.rebates.model.UserLoginInfo;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.common.ResultCode;
import com.office.rebates.model.request.CreateOrderRequest;
import com.office.rebates.service.RebatesOrderService;
import com.office.rebates.service.UserService;
import com.office.rebates.util.CookieHelper;
import com.office.rebates.util.PropertiesUtils;


@Controller
@RequestMapping(RouteKey.PREFIX_AJAX+"/"+RouteKey.REBATES_USER)
public class RebatesUserController {
    @Autowired
    private UserService userService;
    
    
    static Logger logger = LoggerFactory.getLogger(RebatesUserController.class);
	
    //注册用户
    @ResponseBody
	@RequestMapping(value = RouteKey.REGISTER_USER, method = RequestMethod.POST)
	public ResultCode<UserLoginInfo> createRebatesOrder(String userName,String password) {
    	logger.info("registering user with name:"+userName+",password:"+password);
		ResultCode<UserLoginInfo> result=new ResultCode<UserLoginInfo>();		
		//check params
		if(userName==null||password==null){
			result.setErrCode(Messages.MISSING_REQUIRED_PARAM_CODE);
			result.setErrMsg(Messages.MISSING_REQUIRED_PARAM_MSG);
			return result;
		}
		
		//create the user
		try {
			UserLoginInfo userLoginInfo=userService.registerUser(userName,password);
			result.setData(userLoginInfo);
		} catch (RebatesException e) {
			logger.error("fail to register user",e);
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}catch (Exception e) {
			logger.error("fail to register user for unexpected reason",e);
			result.setErrCode(Messages.UPEXPECTED_ERROR_CODE);
			result.setErrMsg(e.getMessage());
		}
		return result;

	}
    
    //用户登陆
    @ResponseBody
	@RequestMapping(value = RouteKey.LOGIN, method = RequestMethod.POST)
	public ResultCode<UserLoginInfo> lonin(String userName,String password,HttpServletResponse response) {
    	logger.info("user with name:"+userName+",password:"+password+" is logging in");
		ResultCode<UserLoginInfo> result=new ResultCode<UserLoginInfo>();		
		//check params
		if(userName==null||password==null){
			result.setErrCode(Messages.MISSING_REQUIRED_PARAM_CODE);
			result.setErrMsg(Messages.MISSING_REQUIRED_PARAM_MSG);
			return result;
		}
		
		try {
			UserLoginInfo userLoginInfo=userService.login(userName, password);
			result.setData(userLoginInfo);
			setLoginCookie(userLoginInfo.getName(), userLoginInfo.getToken(), response);
			
		} catch (RebatesException e) {
			logger.error("fail to login user",e);
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}catch (Exception e) {
			logger.error("fail to login user for unexpected reason",e);
			result.setErrCode(Messages.UPEXPECTED_ERROR_CODE);
			result.setErrMsg(e.getMessage());
		}
		return result;
		


	}
    
    /**
     * 登录成功之后种cookie，包括sid和token
     * @param sid
     * @param token
     * @param response
     */
    private void setLoginCookie(String name, String token, HttpServletResponse response) {
        //set user_token
        CookieHelper.setCookie(
                Constants.COOKIE_USER_TOKEN
                , token
                , PropertiesUtils.prop.get("domain.name")
                , "/"
                , 30 * 24 * 60 * 60  //cookie有效期30天
                , response
        );
        //set user_name
        CookieHelper.setCookie(
        		Constants.COOKIE_USER_NAME
                , name
                , PropertiesUtils.prop.get("domain.name")
                , "/"
                , 30 * 24 * 60 * 60  //cookie有效期30天
                , response
        );
    }
}
