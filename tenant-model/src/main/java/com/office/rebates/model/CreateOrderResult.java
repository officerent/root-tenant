package com.office.rebates.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreateOrderResult implements Serializable{	

	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getSoho3qOrder() {
		return soho3qOrder;
	}
	public void setSoho3qOrder(String soho3qOrder) {
		this.soho3qOrder = soho3qOrder;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	private Long orderId;
	private String soho3qOrder; //soho3q需求单id或者order_num(对于券系统)
	private String salesName; //销售名称

	
}
