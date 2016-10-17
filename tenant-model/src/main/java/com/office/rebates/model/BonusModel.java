package com.office.rebates.model;

import java.math.BigDecimal;

public class BonusModel {


	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public BigDecimal getRebatesAmount() {
		return rebatesAmount;
	}
	public void setRebatesAmount(BigDecimal rebatesAmount) {
		this.rebatesAmount = rebatesAmount;
	}
	private String customerName;
	private String customerMobile;
	private String lastUpdateTime;  //获得返利时间 yyyy-mm-dd hh:MM:ss
	private BigDecimal rebatesAmount;	 //返利金额，单位圆

}
