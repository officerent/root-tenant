package com.office.rebates.controller.admin;

import com.office.rebates.controller.RouteKey;
import com.office.rebates.model.admin.RebatesOrderModel;
import com.office.rebates.model.common.Page;
import com.office.rebates.service.admin.AdminOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.jar.Attributes;

/**
 * 订单控制器
 * Created by liyongfeng on 2016/3/6.
 */
@Controller
@RequestMapping(RouteKey.ORDER)
public class AdminOrderController {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AdminOrderController.class);

    /**
     * 订单服务
     */
    @Autowired
    private AdminOrderService adminOrderService;

    /**
     * 获取订单列表
     * @param page 分页
     * @param rebatesOrderModel 订单实体
     * @param model 载体
     */
    @RequestMapping(RouteKey.ORDER_LIST)
    public void orderList(Page page,RebatesOrderModel rebatesOrderModel,Model model){
        model.addAttribute("data",adminOrderService.selectRebatesOrderList(page,rebatesOrderModel));
        model.addAttribute("page",page);
        model.addAttribute("order",rebatesOrderModel);
    }

    /**
     * 查询订单详情
     * @param orderId 订单id
     * @param model 载体
     */
    @RequestMapping(RouteKey.SEE_ONE_ORDER)
    public void seeOneOrder(long orderId,Model model){
        model.addAttribute("order",adminOrderService.selectRebatesOrderOne(orderId));
        model.addAttribute("orderItemList",adminOrderService.selectOrderItemList(orderId));
    }

    /**
     * 更新订单奖励状态
     * @param orderId 订单id
     * @param attributes 载体
     * @return
     */
    @RequestMapping(RouteKey.UPDATE_ORDER)
    public String updateOrder(long orderId,RedirectAttributes attributes){
        try {
            attributes.addFlashAttribute("message",adminOrderService.updateOrderStatus(orderId));
        } catch (Exception e) {
            logger.error("update order bonus status have a error",e);
        }
        return "redirect:order_list.html";
    }
}
