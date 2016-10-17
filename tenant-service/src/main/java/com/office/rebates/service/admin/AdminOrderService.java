package com.office.rebates.service.admin;

import com.office.rebates.dal.dataobj.RebatesOrderItem;
import com.office.rebates.model.OrderItem;
import com.office.rebates.model.admin.RebatesOrderModel;
import com.office.rebates.model.common.Page;

import java.util.List;

/**
 * 订单服务
 * Created by liyongfeng on 2016/3/6.
 */
public interface AdminOrderService {

    /**
     * 获取订单服务列表
     * @param page 分页
     * @param rebatesOrderModel 订单实体
     * @return
     */
    public List<RebatesOrderModel> selectRebatesOrderList(Page page,RebatesOrderModel rebatesOrderModel);


    /**
     * 获取订单详尽信息
     * @param orderId 订单id
     * @return
     */
    public RebatesOrderModel selectRebatesOrderOne(long orderId);

    /**
     * 获取订单详情列表
     * @param orderId 订单id
     * @return
     */
    public List<RebatesOrderItem> selectOrderItemList(long orderId);

    /**
     * 更新订单状态
     * @param orderId 订单id
     * @return
     */
    public int updateOrderStatus(long orderId) throws Exception;


}
