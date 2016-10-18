package com.root.tenant.controller.ajax;

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
import com.root.tenant.controller.RouteKey;
import com.alibaba.fastjson.JSON;
import com.root.tenant.model.common.ResultCode;



@Controller
@RequestMapping(RouteKey.PREFIX_AJAX)
public class TestController {
    @Autowired
  
    
    
    static Logger logger = LoggerFactory.getLogger(TestController.class);
	
    //获取返利率
    @ResponseBody
	@RequestMapping(value = RouteKey.TEST_API, method = RequestMethod.GET)
	public ResultCode<Object> getRebatesRatio() {
    	ResultCode<Object> result=new ResultCode<Object>();		
		
		
		return result;

	}
    
}
