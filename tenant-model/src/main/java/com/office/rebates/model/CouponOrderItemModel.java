package com.office.rebates.model;


public class CouponOrderItemModel {


	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Long getGiftCouponId() {
		return giftCouponId;
	}
	public void setGiftCouponId(Long giftCouponId) {
		this.giftCouponId = giftCouponId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	private Long couponId;
	private Integer amount;
	private Long giftCouponId;
	private Double price;//单位圆


}
