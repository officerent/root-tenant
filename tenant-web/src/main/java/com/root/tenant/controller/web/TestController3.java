package com.root.tenant.controller.web;

import com.root.tenant.controller.RouteKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * web端控制器
 * .
 */
@Controller
@RequestMapping(RouteKey.TEST_API)
public class TestController3 {


    private static Logger logger = LoggerFactory.getLogger(TestController3.class);
    /**
     * web端首页
     */
    @RequestMapping(RouteKey.TEST)
    public void index(){

    }
    
}
