package com.office.rebates.service.admin.impl;

import com.office.rebates.dal.dao.NewRebatesOrderMapper;
import com.office.rebates.dal.dao.RebatesBonusMapper;
import com.office.rebates.dal.dao.RebatesOrderItemMapper;
import com.office.rebates.dal.dataobj.RebatesBonus;
import com.office.rebates.dal.dataobj.RebatesBonusExample;
import com.office.rebates.dal.dataobj.RebatesOrderItem;
import com.office.rebates.dal.dataobj.RebatesOrderItemExample;
import com.office.rebates.model.OrderItem;
import com.office.rebates.model.admin.RebatesOrderModel;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Page;
import com.office.rebates.service.admin.AdminOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单服务
 * Created by liyongfeng on 2016/3/6.
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AdminOrderServiceImpl.class);

    /**
     * 新订单dao
     */
    @Autowired
    public NewRebatesOrderMapper newRebatesOrderMapper;

    /**
     * 订单详情dao
     */
    @Autowired
    public RebatesOrderItemMapper rebatesOrderItemMapper;

    /**
     * 返利dao
     */
    @Autowired
    RebatesBonusMapper rebatesBonusMapper;

    /**
     * 获取订单列表
     *
     * @param page              分页
     * @param rebatesOrderModel 订单实体
     * @return
     */
    @Override
    public List<RebatesOrderModel> selectRebatesOrderList(Page page, RebatesOrderModel rebatesOrderModel) {
        List<RebatesOrderModel> rebatesOrderModels = new ArrayList<RebatesOrderModel>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("rebatesName", rebatesOrderModel.getName());
            map.put("customerName", rebatesOrderModel.getCustomerName());
            map.put("customerMobile", rebatesOrderModel.getCustomerMobile());
            map.put("salesName", rebatesOrderModel.getUserName());
            map.put("soho3qOrderId", rebatesOrderModel.getSoho3qOrderId());
            page.setTotalElements(newRebatesOrderMapper.countOrderList(map));
            map.put("start", page.getNumber() * page.getSize());
            map.put("size", page.getSize());
            rebatesOrderModels = newRebatesOrderMapper.selectOrderList(map);
        } catch (Exception e) {
            logger.error("查询订单列表 selectRebatesOrderList", e);
        }
        return rebatesOrderModels;
    }

    /**
     * 查询订单详情
     *
     * @param orderId 订单id
     * @return
     */
    @Override
    public RebatesOrderModel selectRebatesOrderOne(long orderId) {
        RebatesOrderModel rebatesOrderModel = new RebatesOrderModel();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("orderId",orderId);
            rebatesOrderModel = newRebatesOrderMapper.selectOrderOne(map);
        } catch (Exception e) {
            logger.error("查询订单详情selectRebatesOrderOne", e);
        }
        return rebatesOrderModel;
    }

    /**
     * 获取订单详情列表
     *
     * @param orderId 订单id
     * @return
     */
    @Override
    public List<RebatesOrderItem> selectOrderItemList(long orderId) {
        List<RebatesOrderItem> orderItems = new ArrayList<RebatesOrderItem>();
        RebatesOrderItemExample example = new RebatesOrderItemExample();
        try {
            example.createCriteria().andOrderIdEqualTo(orderId);
            orderItems = rebatesOrderItemMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("查询订单明细出错：selectOrderItemList", e);
        }
        return orderItems;
    }

    /**
     * 更改订单返利状态
     * @param orderId 订单id
     * @return
     * @throws Exception
     */
    @Override
    public int updateOrderStatus(long orderId) throws Exception {
        RebatesBonusExample example = new RebatesBonusExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<RebatesBonus> rebatesBonuses = rebatesBonusMapper.selectByExample(example);
        if (rebatesBonuses != null) {
            for (int i = 0; i < rebatesBonuses.size(); i++) {
                rebatesBonuses.get(i).setStatus(Constants.BONUS_STATUS_REBATES_PAID);
                rebatesBonusMapper.updateByPrimaryKey(rebatesBonuses.get(i));
            }
            return 1;
        }
        return 0;
    }


}
