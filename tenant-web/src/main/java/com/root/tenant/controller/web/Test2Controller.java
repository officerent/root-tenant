package com.root.tenant.controller.web;

import com.root.tenant.controller.RouteKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * web端控制器
 * .
 */
@Controller
//@RequestMapping(RouteKey.WEB) 
public class Test2Controller {


    private static Logger logger = LoggerFactory.getLogger(Test2Controller.class);
    /**
     * web端首页
     */
    @RequestMapping(RouteKey.TEST)
    public void index(){

    }
    
}
