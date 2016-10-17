package com.office.rebates.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderModel implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8424127768332987747L;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getSoho3qOrderNum() {
		return soho3qOrderNum;
	}
	public void setSoho3qOrderNum(String soho3qOrderNum) {
		this.soho3qOrderNum = soho3qOrderNum;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public BigDecimal getRebatesAmount() {
		return rebatesAmount;
	}
	public void setRebatesAmount(BigDecimal rebatesAmount) {
		this.rebatesAmount = rebatesAmount;
	}
	private Long orderId;
	private String orderDate; //yyyy-mm-dd
	private String soho3qOrderNum; //soho3q订单号
	private Byte status; //0=需求单待确认，1=需求单已确认，2=客户已支付订单，3=销售已收到佣金，4=客户已经收到返利,5=订单已取消
	private BigDecimal rebatesAmount;//返利金额，单位元
	
}
