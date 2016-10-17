package com.office.rebates.model;


public class OrderItem {	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public Double getDepositPrice() {
		return depositPrice;
	}
	public void setDepositPrice(Double depositPrice) {
		this.depositPrice = depositPrice;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductSubType() {
		return productSubType;
	}
	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}
	public Integer getBookNum() {
		return bookNum;
	}
	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}
//	public Double getTotalPrice() {
//		return totalPrice;
//	}
//	public void setTotalPrice(Double totalPrice) {
//		this.totalPrice = totalPrice;
//	}
	private String projectId;
	private Double originalPrice;//原单价，单位元
	private Double finalPrice;//最终单价，单位元
	private Double depositPrice;//押金单价，单位元
	private String productType; //产品大类
	private String productSubType; //产品小类
	private Integer bookNum; //预订数量
	//private Double totalPrice;//总价，单位元
	
}
