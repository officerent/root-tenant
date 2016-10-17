package com.office.rebates.model.request;


/**
 * Created by qiuliang on 15/9/17.
 */
public class SubmitOrderRequest {
    

    public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkbenchId() {
		return workbenchId;
	}
	public void setWorkbenchId(String workbenchId) {
		this.workbenchId = workbenchId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getProdInfoKey() {
		return prodInfoKey;
	}
	public void setProdInfoKey(String prodInfoKey) {
		this.prodInfoKey = prodInfoKey;
	}
	private String customer;
    private String phone;
    private String email;
    private String workbenchId;
    private String startDate;
    private String endDate;
    private String company;
    private String prodInfoKey;


}
