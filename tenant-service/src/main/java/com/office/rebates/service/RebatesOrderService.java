package com.office.rebates.service;

import java.util.List;

import com.office.rebates.model.CreateOrderResult;
import com.office.rebates.model.OrderModel;
import com.office.rebates.model.UserInfo;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.request.CreateOrderRequest;
import com.office.rebates.model.request.CreateCouponOrderRequest;

public interface RebatesOrderService {

	CreateOrderResult createRebatesOrder(CreateOrderRequest request, UserInfo userInfo) throws RebatesException;
	
	CreateOrderResult createRebatesCouponOrder(CreateCouponOrderRequest request, UserInfo userInfo) throws RebatesException;

	Integer getMyOrderNum(Long userId);

	List<OrderModel> getMyOrders(Long userId, Integer pageSize, Integer pageNum);
	
}
