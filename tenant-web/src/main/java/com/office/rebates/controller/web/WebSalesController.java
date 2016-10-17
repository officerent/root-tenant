package com.office.rebates.controller.web;

import com.alibaba.fastjson.JSON;
import com.office.rebates.controller.RouteKey;
import com.office.rebates.model.Soho3qProjectModel;
import com.office.rebates.model.UserInfo;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.Page;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.service.RebatesOrderService;
import com.office.rebates.service.Soho3qAccessService;
import com.office.rebates.service.UserService;
import com.office.rebates.service.web.WebSalesService;
import com.office.rebates.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 下单控制器
 * Created by liyongfeng on 2016/3/20.
 */
@Controller
@RequestMapping(RouteKey.SALES)
public class WebSalesController {

    /**
     * 下单服务
     */
    @Autowired
    private WebSalesService webSalesService;
    
    @Autowired
    private UserService userService;
        
    @Autowired
    private RebatesOrderService rebatesOrderService;

    /**
     * 税率
     */
    private static final double tax = 0.06d;

    /**
     * 下券订单
     */
    @RequestMapping(RouteKey.COUPON_ORDER)
    public String couponOrder(HttpServletRequest httpServletRequest,Model model,String source){
        Cookie[] cookies=httpServletRequest.getCookies();
        UserInfo userInfo=userService.getUserInfo(cookies);
        if(userInfo==null||userInfo.getUserId()==null){
            return "redirect:/user/login.html";
        }
        model.addAttribute("couponList",webSalesService.getCouponList(source));
        model.addAttribute("member",webSalesService.getMemberCoupon());
        model.addAttribute("source",source);
        model.addAttribute("tax",tax);
        return "sales/coupon_order";
    }

    /**
     * 下传统订单页
     * @param model 载体
     */
    @RequestMapping(RouteKey.STATION_ORDER)
    public String stationOrder(HttpServletRequest httpServletRequest,Model model){
        Cookie[] cookies=httpServletRequest.getCookies();
        UserInfo userInfo=userService.getUserInfo(cookies);
        if(userInfo==null||userInfo.getUserId()==null){
            return "redirect:/user/login.html";
        }
        String date = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd");
        List<Soho3qProjectModel> projectList=webSalesService.getProjectList();
        model.addAttribute("project",projectList);
        model.addAttribute("startTime", date);
        model.addAttribute("tax",tax);
        model.addAttribute("productList", webSalesService.getProductList(projectList.get(0).getProjectId(),date,DateUtil.addDayOfYear(date,30)));
        return "sales/station_order";
    }
    
    /**
     * 我的订单列表
     * @param model 载体
     */
    @RequestMapping(RouteKey.ORDER_LIST)
    public String myOrder(HttpServletRequest httpServletRequest,Page page,Model model){
		//check if the user is logon
		Cookie[] cookies=httpServletRequest.getCookies();
		UserInfo userInfo=userService.getUserInfo(cookies);
		if(userInfo==null||userInfo.getUserId()==null){
			return "redirect:/user/login.html";
		}
		page.setTotalElements(rebatesOrderService.getMyOrderNum(userInfo.getUserId()));
        model.addAttribute("orderList",rebatesOrderService.getMyOrders(userInfo.getUserId(), page.getSize(), page.getNumber()));
        model.addAttribute("page",page);
		return "sales/order_list";
    }
}
