package com.office.rebates.controller.web;

import com.office.rebates.controller.RouteKey;
import com.office.rebates.model.BonusModel;
import com.office.rebates.model.RebatesRatio;
import com.office.rebates.service.RebatesInfoService;
import com.office.rebates.service.web.WebIndexService;
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
 * Created by liyongfeng on 2016/3/13.
 */
@Controller
//@RequestMapping(RouteKey.WEB)
public class WebIndexController {

    /**
     * 服务
     */
    @Autowired
    private WebIndexService webIndexService;

    /**
     * 返佣服务
     */
    @Autowired
    private RebatesInfoService rebatesInfoService;

    private static Logger logger = LoggerFactory.getLogger(WebIndexController.class);
    /**
     * web端首页
     */
    @RequestMapping(RouteKey.WEB_INDEX)
    public void index(){

    }
    
}
