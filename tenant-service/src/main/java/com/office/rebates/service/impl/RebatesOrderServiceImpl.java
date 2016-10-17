package com.office.rebates.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.office.rebates.dal.cache.CacheClient;
import com.office.rebates.dal.dao.NewRebatesOrderMapper;
import com.office.rebates.dal.dao.NewSalesPeopleMapper;
import com.office.rebates.dal.dao.RebatesOrderItemMapper;
import com.office.rebates.dal.dao.RebatesOrderMapper;
import com.office.rebates.dal.dao.SalesPeopleMapper;
import com.office.rebates.dal.dataobj.RebatesOrder;
import com.office.rebates.dal.dataobj.RebatesOrderExample;
import com.office.rebates.dal.dataobj.RebatesOrderItem;
import com.office.rebates.dal.dataobj.SalesPeople;
import com.office.rebates.dal.dataobj.SalesPeopleExample;
import com.office.rebates.dal.rest.Soho3qCheckOrderApi;
import com.office.rebates.dal.rest.Soho3qCreateCouponOrderApi;
import com.office.rebates.dal.rest.Soho3qCreateOrderApi;
import com.office.rebates.model.CouponOrderItemModel;
import com.office.rebates.model.CreateOrderResult;
import com.office.rebates.model.OrderItem;
import com.office.rebates.model.OrderModel;
import com.office.rebates.model.Soho3qOrder;
import com.office.rebates.model.UserInfo;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.request.CreateCouponOrderRequest;
import com.office.rebates.model.request.CreateOrderRequest;
import com.office.rebates.service.RebatesOrderService;
import com.office.rebates.util.DateUtil;
import com.office.rebates.util.PropertiesUtils;


@Service
@Transactional(value = "rebatesTransactionManager", rollbackFor = Exception.class)
public class RebatesOrderServiceImpl implements RebatesOrderService{

	static Logger logger = LoggerFactory.getLogger(RebatesOrderServiceImpl.class);
	
    @Autowired
    private RebatesOrderMapper rebatesOrderMapper;
    
    @Autowired
    private RebatesOrderItemMapper rebatesOrderItemMapper;
    
    @Autowired
    private SalesPeopleMapper salesPeopleMapper;
    
	@Autowired
	private Soho3qCreateOrderApi soho3qCreateOrderApi;
	
	@Autowired
	private Soho3qCreateCouponOrderApi soho3qCreateCouponOrderApi;
	
	@Autowired
	private NewSalesPeopleMapper newSalesPeopleMapper;
	
    @Autowired
    private NewRebatesOrderMapper newRebatesOrderMapper;
	
	@Override
	public CreateOrderResult createRebatesOrder(CreateOrderRequest request, UserInfo userInfo) throws RebatesException {
		//select a sales people to place to soho3q order
		CreateOrderResult createOrderResult=new CreateOrderResult();
		Date now=new Date();
		SalesPeople people=getSalesPeople();
		logger.info("got sales people to place order:"+JSON.toJSONString(people));
		
		//lock the sales people,确保一个销售不能同时下两个订单
		newSalesPeopleMapper.getAndLockSalesPeople(people.getSalesId());
		createOrderResult.setSalesName(people.getName());
		//place order to soho3q
		Long soho3qOrderId=soho3qCreateOrderApi.createSoho3qOrder(request,people);
		createOrderResult.setSoho3qOrder(soho3qOrderId.toString());
		logger.info("created soho3q order with id:"+soho3qOrderId);
		if(soho3qOrderId==null){
			logger.error("fail to create soho3q order");
			throw new RebatesException(Messages.FAIL_TO_CREATE_SOHO3Q_ORDER_CODE,Messages.FAIL_TO_CREATE_SOHO3Q_ORDER_MSG);
		}
		
		//insert rebates order
		RebatesOrder rebatesOrder=new RebatesOrder();
		rebatesOrder.setCheckinDate(DateUtil.parseDate(request.getCheckInDate(), DateUtil.FORMAT_DEFAULT));
		rebatesOrder.setCheckoutDate(DateUtil.parseDate(request.getCheckOutDate(), DateUtil.FORMAT_DEFAULT));
		rebatesOrder.setCreateTime(now);
		rebatesOrder.setCustomerAlipay(request.getCustomerAlipay());
		rebatesOrder.setCustomerCompany(request.getCustomerCompany());
		rebatesOrder.setCustomerMobile(request.getCustomerMobile());
		rebatesOrder.setCustomerName(request.getCustomerName());
		Double depositAmountCent=request.getDepositAmount()*100;
		rebatesOrder.setDepositAmount(depositAmountCent.intValue());
		rebatesOrder.setLastUpdateTime(now);
		Double leaseAmountCent=request.getLeaseAmount()*100;
		rebatesOrder.setLeaseAmount(leaseAmountCent.intValue());
		rebatesOrder.setPeriodMonth(request.getPeriodMonth());
		rebatesOrder.setPeriodWeek(request.getPeriodWeek());
		rebatesOrder.setPorjectId(request.getProjectId());
		rebatesOrder.setPorjectName(request.getProjectName());
		rebatesOrder.setSalesId(people.getSalesId());
		rebatesOrder.setSoho3qOrderId(soho3qOrderId);
		rebatesOrder.setUserId(userInfo.getUserId());
		BigDecimal rebatesRatio=new BigDecimal(PropertiesUtils.prop.get("rebate_ratio"));
		rebatesOrder.setRebatesRatio(rebatesRatio);
		rebatesOrderMapper.insert(rebatesOrder);
		createOrderResult.setOrderId(rebatesOrder.getOrderId());
		logger.info("created soho3q order :"+JSON.toJSONString(rebatesOrder));
		
		//insert rebates order items
		if(request.getOrderItems()!=null&&!request.getOrderItems().isEmpty()){
			for(OrderItem orderItem:request.getOrderItems()){
				RebatesOrderItem rebatesOrderItem=new RebatesOrderItem();
				rebatesOrderItem.setBookNum(orderItem.getBookNum());
				rebatesOrderItem.setCreateTime(now);
				Double depositCent=orderItem.getDepositPrice()*100;
				rebatesOrderItem.setDepositPrice(depositCent.intValue());
				Double finalPriceCent=orderItem.getDepositPrice()*100;
				rebatesOrderItem.setFinalPrice(finalPriceCent.intValue());
				rebatesOrderItem.setLastUpdateTime(now);
				rebatesOrderItem.setOrderId(rebatesOrder.getOrderId());
				Double originalPriceCent=orderItem.getOriginalPrice()*100;
				rebatesOrderItem.setOriginalPrice(originalPriceCent.intValue());
				rebatesOrderItem.setPorjectId(orderItem.getProjectId());
				rebatesOrderItem.setProductSubType(orderItem.getProductSubType());
				rebatesOrderItem.setProductType(orderItem.getProductType());
				Double totalPriceCent=(finalPriceCent+depositCent)*orderItem.getBookNum();
				rebatesOrderItem.setTotalPrice(totalPriceCent.intValue());
				rebatesOrderItemMapper.insert(rebatesOrderItem);
				logger.info("created soho3q order item :"+JSON.toJSONString(rebatesOrderItem));
			}
		}
		return createOrderResult;

	}

	private SalesPeople getSalesPeople() throws RebatesException {
		SalesPeopleExample salesPeopleExample=new SalesPeopleExample();
		salesPeopleExample.createCriteria();
		List<SalesPeople> salesPeoples=salesPeopleMapper.selectByExample(salesPeopleExample);
		
		if(salesPeoples==null||salesPeoples.size()==0){
			logger.error("no sales people configured");
			throw new RebatesException(Messages.NO_SALES_PEOPLE_FOUND_CODE,Messages.NO_SALES_PEOPLE_FOUND_MSG);
		}
		int size=salesPeoples.size();
		Date now =new Date();
		Long random=now.getTime();
		Long mod=random%size;
		
		int index=mod.intValue();
		return salesPeoples.get(index);
	}

	@Override
	public CreateOrderResult createRebatesCouponOrder(CreateCouponOrderRequest request, UserInfo userInfo) throws RebatesException {
		//select a sales people to place to soho3q order
		Date now=new Date();
		CreateOrderResult createOrderResult=new CreateOrderResult();
		SalesPeople people=getSalesPeople();
		logger.info("got sales people to place order:"+JSON.toJSONString(people));
		
		//lock the sales people,确保一个销售不能同时下两个订单
		newSalesPeopleMapper.getAndLockSalesPeople(people.getSalesId());
		createOrderResult.setSalesName(people.getName());
		
		//place order to soho3q
		Long soho3qOrderId=soho3qCreateCouponOrderApi.createSoho3qCouponOrder(request,people);
		logger.info("created soho3q order with id:"+soho3qOrderId);
		if(soho3qOrderId==null){
			logger.error("fail to create soho3q order");
			throw new RebatesException(Messages.FAIL_TO_CREATE_SOHO3Q_ORDER_CODE,Messages.FAIL_TO_CREATE_SOHO3Q_ORDER_MSG);
		}
		createOrderResult.setSoho3qOrder(soho3qOrderId.toString());
		
		//insert rebates order
		RebatesOrder rebatesOrder=new RebatesOrder();
		//rebatesOrder.setCheckinDate(DateUtil.parseDate(request.getCheckInDate(), DateUtil.FORMAT_DEFAULT));
		//rebatesOrder.setCheckoutDate(DateUtil.parseDate(request.getCheckOutDate(), DateUtil.FORMAT_DEFAULT));
		rebatesOrder.setCreateTime(now);
		rebatesOrder.setCustomerAlipay(request.getCustomerAlipay());
		rebatesOrder.setCustomerCompany(request.getCustomerCompany());
		rebatesOrder.setCustomerMobile(request.getCustomerMobile());
		rebatesOrder.setCustomerName(request.getCustomerName());
		//Double depositAmountCent=request.getDepositAmount()*100;
		//rebatesOrder.setDepositAmount(depositAmountCent.intValue());
		rebatesOrder.setLastUpdateTime(now);
		
		Double totalPrice=0d;
		for(CouponOrderItemModel item:request.getCouponOrderItems()){
			if(item.getPrice()!=null&&item.getAmount()!=null){
				totalPrice+=item.getPrice()*item.getAmount();
			}
		}
		
		Double leaseAmountCent=totalPrice*100;
		rebatesOrder.setLeaseAmount(leaseAmountCent.intValue());
		//rebatesOrder.setPeriodMonth(request.getPeriodMonth());
		//rebatesOrder.setPeriodWeek(request.getPeriodWeek());
		//rebatesOrder.setPorjectId(request.getProjectId());
		//rebatesOrder.setPorjectName(request.getProjectName());
		rebatesOrder.setSalesId(people.getSalesId());
		rebatesOrder.setSoho3qOrderId(soho3qOrderId);
		rebatesOrder.setUserId(userInfo.getUserId());
		BigDecimal rebatesRatio=new BigDecimal(PropertiesUtils.prop.get("rebate_ratio"));
		rebatesOrder.setRebatesRatio(rebatesRatio);
		rebatesOrderMapper.insert(rebatesOrder);
		logger.info("created soho3q order :"+JSON.toJSONString(rebatesOrder));
		
		//insert rebates order items
		if(request.getCouponOrderItems()!=null&&!request.getCouponOrderItems().isEmpty()){
			for(CouponOrderItemModel orderItem:request.getCouponOrderItems()){
				RebatesOrderItem rebatesOrderItem=new RebatesOrderItem();
				//rebatesOrderItem.setBookNum(orderItem.getBookNum());
				rebatesOrderItem.setCreateTime(now);
				//Double depositCent=orderItem.getDepositPrice()*100;
				//rebatesOrderItem.setDepositPrice(depositCent.intValue());
				Double priceCent=orderItem.getPrice()*100;//产品单价，单位分
				rebatesOrderItem.setFinalPrice(priceCent.intValue());
				rebatesOrderItem.setLastUpdateTime(now);
				rebatesOrderItem.setOrderId(rebatesOrder.getOrderId());
				//Double originalPriceCent=orderItem.getOriginalPrice()*100;
				rebatesOrderItem.setOriginalPrice(priceCent.intValue());
				//rebatesOrderItem.setPorjectId(orderItem.getProjectId());
				//rebatesOrderItem.setProductSubType(orderItem.getProductSubType());
				//rebatesOrderItem.setProductType(orderItem.getProductType());
				Double totalPriceCent=priceCent*orderItem.getAmount();
				rebatesOrderItem.setTotalPrice(totalPriceCent.intValue());
				rebatesOrderItemMapper.insert(rebatesOrderItem);
				logger.info("created soho3q order item :"+JSON.toJSONString(rebatesOrderItem));
			}
		}
		createOrderResult.setOrderId(rebatesOrder.getOrderId());
		return createOrderResult;
	}

	@Override
	public Integer getMyOrderNum(Long userId) {
		RebatesOrderExample rebatesOrderExample=new RebatesOrderExample();
		rebatesOrderExample.createCriteria().andUserIdEqualTo(userId);
		int count=rebatesOrderMapper.countByExample(rebatesOrderExample);
		return count;
	}

	@Override
	public List<OrderModel> getMyOrders(Long userId, Integer pageSize, Integer pageNum) {
		Integer start=pageNum*pageSize;
		Integer size=pageSize;
		
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("start",start);
        map.put("size",size);
        map.put("userId",userId);
        List<OrderModel> orders= newRebatesOrderMapper.getMyOrderList(map);
        for(OrderModel order:orders){
        	if(order.getStatus()==null){
        		order.setStatus((byte) 0);
        	}
        	
        	if(order.getStatus()==0||order.getStatus()==1){
        		order.setRebatesAmount(null);;
        	}
        }
        logger.info("my orders is :"+JSON.toJSONString(orders));
		return orders;
	}



}
