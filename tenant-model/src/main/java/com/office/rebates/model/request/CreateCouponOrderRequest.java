package com.office.rebates.model.request;

import java.util.List;

import com.office.rebates.model.CouponOrderItemModel;

public class CreateCouponOrderRequest {
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCompany() {
		return customerCompany;
	}
	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}
	public String getCustomerAlipay() {
		return customerAlipay;
	}
	public void setCustomerAlipay(String customerAlipay) {
		this.customerAlipay = customerAlipay;
	}
	public List<CouponOrderItemModel> getCouponOrderItems() {
		return couponOrderItems;
	}
	public void setCouponOrderItems(List<CouponOrderItemModel> couponOrderItems) {
		this.couponOrderItems = couponOrderItems;
	}
	private String customerMobile;
	private String customerName;
	private String customerCompany;
	private String customerAlipay;
	private List<CouponOrderItemModel> couponOrderItems;
	
}
