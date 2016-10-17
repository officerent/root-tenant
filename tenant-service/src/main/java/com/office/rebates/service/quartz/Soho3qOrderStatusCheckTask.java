package com.office.rebates.service.quartz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.office.rebates.dal.dao.NewRebatesGlobalLockMapper;
import com.office.rebates.dal.dao.RebatesBonusMapper;
import com.office.rebates.dal.dao.RebatesOrderMapper;
import com.office.rebates.dal.dao.SalesPeopleMapper;
import com.office.rebates.dal.dataobj.RebatesBonus;
import com.office.rebates.dal.dataobj.RebatesBonusExample;
import com.office.rebates.dal.dataobj.RebatesGlobalLock;
import com.office.rebates.dal.dataobj.RebatesOrder;
import com.office.rebates.dal.dataobj.RebatesOrderExample;
import com.office.rebates.dal.dataobj.SalesPeople;
import com.office.rebates.dal.dataobj.SalesPeopleExample;
import com.office.rebates.dal.rest.Soho3qCheckOrderApi;
import com.office.rebates.model.Soho3qOrder;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.util.PropertiesUtils;

@Transactional(value = "rebatesTransactionManager", rollbackFor = Exception.class)
public class Soho3qOrderStatusCheckTask {
	
	@Autowired
	private SalesPeopleMapper salesPeopleMapper;
	
	@Autowired
	private RebatesBonusMapper rebatersBonusMapper;
	
	@Autowired
	private RebatesOrderMapper rebatersOrderMapper;
	
	@Autowired
	private Soho3qCheckOrderApi soho3qCheckOrderApi;
	
	@Autowired
	private NewRebatesGlobalLockMapper newRebatesGlobalLockMapper;
		
	
	static Logger logger = LoggerFactory.getLogger(Soho3qOrderStatusCheckTask.class);
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM"); 
	
	public void doWork() {		
		logger.info("start to check soho3q order status...");	
		
		RebatesGlobalLock globalLock=newRebatesGlobalLockMapper.lockResource(Constants.LOCK_PULL_SOHO3Q_ORDERS);
		if(globalLock!=null){
			logger.info("got the global lock for refreshing order status.");
		}else{//没有拿到lock,说明其他实例已经hold lock并且正在执行该方法，则不执行该方法
			logger.info("other instance is holding the lock and refreshing order status, quit the method.");
			return;
		}
		
		//获得所有的销售信息
		SalesPeopleExample example=new SalesPeopleExample();
		List<SalesPeople> sales=salesPeopleMapper.selectByExample(example);
		
		for(SalesPeople people:sales){
			logger.info("checking soho3q order status for sales:"+people.getUserName());
			Integer pageNum=1;
			Integer pageSize=1000;//最多只同步最近1000笔订单
			try {
					List<Soho3qOrder> soho3qOrders=soho3qCheckOrderApi.getSoho3qOrderBySales(people,pageNum,pageSize);
					updateRebatesBonusList(soho3qOrders,people);				
			} catch (RebatesException e) {
				logger.error("fail to get orders by sales:"+people.getUserName());
			}
		}
		
	}
	
	//更新rebatesBonus表
	private void updateRebatesBonusList(List<Soho3qOrder> soho3qOrders, SalesPeople people) {
		for(Soho3qOrder soho3qOrder:soho3qOrders){
			logger.info("processing Soho3qOrder :"+JSON.toJSONString(soho3qOrder));
			if(isRebatesPaid(soho3qOrder.getSoho3qOrderId(),soho3qOrder.getOrderSubNum())){//如果已经支付返利，不再更新
				logger.info("rebates were already paid for order:"+JSON.toJSONString(soho3qOrder));
			}else{
				RebatesBonusExample example=new RebatesBonusExample();
				Byte rebatesBonusStatus=getRebatesBonusStatus(soho3qOrder.getStatus(),soho3qOrder.getBonusStatus());
				if(soho3qOrder.getOrderSubNum()!=null){//分期
					example.createCriteria().andSoho3qOrderIdEqualTo(soho3qOrder.getSoho3qOrderId())
					.andSoho3qOrderNumEqualTo(soho3qOrder.getOrderSubNum());
				}else{//非分期
					example.createCriteria().andSoho3qOrderIdEqualTo(soho3qOrder.getSoho3qOrderId())
					.andSoho3qOrderNumIsNull();
				}
				List<RebatesBonus> rebatesBonusList=rebatersBonusMapper.selectByExample(example);
				if(rebatesBonusList.isEmpty()){//需要插入
					logger.info("need to insert Soho3qOrder :"+JSON.toJSONString(soho3qOrder));
					insertRebatesBonus(soho3qOrder,rebatesBonusStatus);
				}else{//更新
					RebatesBonus rebateBonus=rebatesBonusList.get(0);
					logger.info("old rebate bonus :"+JSON.toJSONString(rebateBonus));
					if(rebateBonus.getStatus()!=rebatesBonusStatus){//状态发生了变化需要更新
						logger.info("need to update rebate bonus to:"+JSON.toJSONString(soho3qOrder));
						updateRebatesBonus(rebateBonus,soho3qOrder,rebatesBonusStatus);
					}else{
						logger.info("status is the same so no need to update rebate bonus");
					}					
				}
			}			
		}
		
	}
	
	private void updateRebatesBonus(RebatesBonus oldRebateBonus,Soho3qOrder soho3qOrder, Byte rebatesBonusStatus) {


		RebatesOrderExample orderExample=new RebatesOrderExample();
		orderExample.createCriteria().andSoho3qOrderIdEqualTo(soho3qOrder.getSoho3qOrderId());
		List<RebatesOrder> rebatesOrders=rebatersOrderMapper.selectByExample(orderExample);
		if(rebatesOrders.isEmpty()){//没有找到对应的订单
			logger.info("can't find rebates order for this soho3q order");
			return;
		}else{
			RebatesOrder rebateOrder=rebatesOrders.get(0);
			logger.info("found rebates order for this soho3q order:"+JSON.toJSONString(rebateOrder));
			oldRebateBonus.setOrderId(rebateOrder.getOrderId());			
			oldRebateBonus.setSalesId(rebateOrder.getSalesId());
			
			oldRebateBonus.setBonusAfterTax(convertYuanToCent(soho3qOrder.getBonusAfterTax()));
			oldRebateBonus.setBonusBeforeTax(convertYuanToCent(soho3qOrder.getBonus()));
			oldRebateBonus.setBonusTax(convertYuanToCent(soho3qOrder.getTax()));
			Date now =new Date();
			//oldRebateBonus.setCreateTime(now);
			oldRebateBonus.setLastUpdateTime(now);
			oldRebateBonus.setLeaseAmount(convertYuanToCent(soho3qOrder.getPaymentAmount()));				
			if(soho3qOrder.getPaymentAmount()!=null){
				BigDecimal rebateRatio=rebateOrder.getRebatesRatio();
				Integer paymentCent=convertYuanToCent(soho3qOrder.getPaymentAmount());
				Double rebatesDouble=paymentCent*rebateRatio.doubleValue()/100;
				Integer rebatesAmount=rebatesDouble.intValue();
				oldRebateBonus.setRebatesAmount(rebatesAmount);
			}
			oldRebateBonus.setSoho3qOrderId(soho3qOrder.getSoho3qOrderId());
			oldRebateBonus.setSoho3qOrderNum(soho3qOrder.getOrderSubNum());
			oldRebateBonus.setStatus(rebatesBonusStatus);
			oldRebateBonus.setSoho3qConfirmedOrderNum(soho3qOrder.getOrderNum());
			oldRebateBonus.setUpdater(Constants.SYSTEM);
			rebatersBonusMapper.updateByPrimaryKeySelective(oldRebateBonus);
			logger.info("updated rebates bonus:"+JSON.toJSONString(oldRebateBonus));
		}		
	}

	private void insertRebatesBonus(Soho3qOrder soho3qOrder,Byte rebatesBonusStatus) {
		RebatesOrderExample orderExample=new RebatesOrderExample();
		orderExample.createCriteria().andSoho3qOrderIdEqualTo(soho3qOrder.getSoho3qOrderId());
		List<RebatesOrder> rebatesOrders=rebatersOrderMapper.selectByExample(orderExample);
		if(rebatesOrders.isEmpty()){//没有找到对应的订单
			logger.info("can't find rebates order for this soho3q order");
		}else{
			RebatesBonus rebateBonus=new RebatesBonus();
			rebateBonus.setBonusAfterTax(convertYuanToCent(soho3qOrder.getBonusAfterTax()));
			rebateBonus.setBonusBeforeTax(convertYuanToCent(soho3qOrder.getBonus()));
			rebateBonus.setBonusTax(convertYuanToCent(soho3qOrder.getTax()));
			Date now =new Date();
			rebateBonus.setCreateTime(now);
			rebateBonus.setLastUpdateTime(now);
			rebateBonus.setLeaseAmount(convertYuanToCent(soho3qOrder.getPaymentAmount()));				
			
			RebatesOrder rebateOrder=rebatesOrders.get(0);
			logger.info("found rebates order for this soho3q order:"+JSON.toJSONString(rebateOrder));
			
			if(soho3qOrder.getPaymentAmount()!=null){
				BigDecimal rebateRatio=rebateOrder.getRebatesRatio();
				Integer paymentCent=convertYuanToCent(soho3qOrder.getPaymentAmount());
				Double rebatesDouble=paymentCent*rebateRatio.doubleValue()/100;
				Integer rebatesAmount=rebatesDouble.intValue();
				rebateBonus.setRebatesAmount(rebatesAmount);
			}
			
			rebateBonus.setOrderId(rebateOrder.getOrderId());			
			rebateBonus.setSalesId(rebateOrder.getSalesId());
			rebateBonus.setSoho3qOrderId(soho3qOrder.getSoho3qOrderId());
			rebateBonus.setSoho3qOrderNum(soho3qOrder.getOrderSubNum());
			rebateBonus.setStatus(rebatesBonusStatus);
			rebateBonus.setSoho3qConfirmedOrderNum(soho3qOrder.getOrderNum());
			rebateBonus.setUpdater(Constants.SYSTEM);
			rebatersBonusMapper.insert(rebateBonus);
			logger.info("inserted rebates bonus:"+JSON.toJSONString(rebateBonus));
		}
	}

	private Integer convertYuanToCent(Double bonusAfterTax) {
		Integer cent=null;
		if(bonusAfterTax!=null){
			Double cent2=bonusAfterTax*100;
			cent=cent2.intValue();
		}
		return cent;
	}

	private boolean isRebatesPaid(Long soho3qOrderId, String orderSubNum) {
		boolean isRebatesPaid=false;
		RebatesBonusExample example=new RebatesBonusExample();
		
		if(orderSubNum!=null){
			example.createCriteria().andSoho3qOrderIdEqualTo(soho3qOrderId).andSoho3qOrderNumEqualTo(orderSubNum)
			.andStatusEqualTo(Constants.BONUS_STATUS_REBATES_PAID);
		}else{
			example.createCriteria().andSoho3qOrderIdEqualTo(soho3qOrderId).andSoho3qOrderNumIsNull()
			.andStatusEqualTo(Constants.BONUS_STATUS_REBATES_PAID);
		}

		List<RebatesBonus> bonusList=rebatersBonusMapper.selectByExample(example);
		if(bonusList!=null&&!bonusList.isEmpty()){//找到了一个已经支付返利的订单
			isRebatesPaid=true;
		}
		return isRebatesPaid;
	}

	//获得状态
	private Byte getRebatesBonusStatus(String orderStatus, String bonusStatus) {
		Byte status=Constants.BONUS_STATUS_ORDER_PLACED;
		if("待确认".equals(orderStatus)){
			status=Constants.BONUS_STATUS_ORDER_PLACED;
		}else if("已确认".equals(orderStatus)){
			status=Constants.BONUS_STATUS_ORDER_CONFIRM;
		}else if("已取消".equals(orderStatus)){
			status=Constants.BONUS_STATUS_ORDER_CANCELED;
		}else if("已支付".equals(orderStatus)){
			status=Constants.BONUS_STATUS_ORDER_PAID;
			if("已结算".equals(bonusStatus)){
				status=Constants.BONUS_STATUS_BONUS_PAID;
			}
		}
		
		return status;
	}


	
	
}
