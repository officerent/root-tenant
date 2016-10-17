package com.office.rebates.model.request;

import java.util.List;

import com.office.rebates.model.CouponOrderItemModel;


/**
 * Created by xxm on 16/2/15.
 */
public class SubmitCouponOrderRequest  {

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerCompany() {
		return customerCompany;
	}
	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}
	public List<CouponOrderItemModel> getItems() {
		return items;
	}
	public void setItems(List<CouponOrderItemModel> items) {
		this.items = items;
	}
	private String customerName;
    private String customerPhone;
    private String customerCompany;
    private List<CouponOrderItemModel> items;
 
}
