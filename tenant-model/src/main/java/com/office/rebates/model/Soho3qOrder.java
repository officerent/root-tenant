package com.office.rebates.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Soho3qOrder {
	public Long getSoho3qOrderId() {
		return soho3qOrderId;
	}
	public void setSoho3qOrderId(Long soho3qOrderId) {
		this.soho3qOrderId = soho3qOrderId;
	}
	public Long getSoho3qSalesId() {
		return soho3qSalesId;
	}
	public void setSoho3qSalesId(Long soho3qSalesId) {
		this.soho3qSalesId = soho3qSalesId;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderSubNum() {
		return orderSubNum;
	}
	public void setOrderSubNum(String orderSubNum) {
		this.orderSubNum = orderSubNum;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Integer getBonusRatio() {
		return bonusRatio;
	}
	public void setBonusRatio(Integer bonusRatio) {
		this.bonusRatio = bonusRatio;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public String getBonusStatus() {
		return bonusStatus;
	}
	public void setBonusStatus(String bonusStatus) {
		this.bonusStatus = bonusStatus;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getBonusAfterTax() {
		return bonusAfterTax;
	}
	public void setBonusAfterTax(Double bonusAfterTax) {
		this.bonusAfterTax = bonusAfterTax;
	}
	public String getBonusType() {
		return bonusType;
	}
	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}
	@JSONField(name = "orderId")
	private Long soho3qOrderId;
	
	@JSONField(name = "salesId")
	private Long soho3qSalesId;
	
	private String customerAccount;
	private String customerName;
	private String status;//订单状态
	private String orderNum;//下单的订单号
	private String orderSubNum;  //支付的订单号
	private Double paymentAmount; //租金金额
	private Integer bonusRatio;//佣金率 8表示8%
	private Double bonus;//税前佣金,单位元
	private String bonusStatus; //佣金的状态
	private Double tax; //税，单位元
	private Double bonusAfterTax;  //税后佣金单位元
	private String bonusType;	//佣金类型
}
