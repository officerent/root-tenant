package com.office.rebates.model;


/**
 * Created by xxm on 15/9/14.
 */
public class Soho3qProductModel {




    private String priceType;

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    private String strategyName;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    private long price;

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    private long finalPrice;

    public long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(long finalPrice) {
        this.finalPrice = finalPrice;
    }

    private String leaseDesc;

    public String getLeaseDesc() {
        return leaseDesc;
    }

    public void setLeaseDesc(String leaseDesc) {
        this.leaseDesc = leaseDesc;
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    private long deposit;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    private String productType;

    private String productSubtype;

    public String getProductSubtype() {
        return productSubtype;
    }

    public void setProductSubtype(String productSubtype) {
        this.productSubtype = productSubtype;
    }

    public int getRemainedNum() {
        return remainedNum;
    }

    public void setRemainedNum(int remainedNum) {
        this.remainedNum = remainedNum;
    }

    private int remainedNum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }



    //该字段代表后端返回的某个产品的总价（周期 * 折扣价）
    private long totalPrice;

    //以下为扩展字段
    private String title;

    public String getPriceTypeStr() {
        return priceTypeStr;
    }

    public void setPriceTypeStr(String priceTypeStr) {
        this.priceTypeStr = priceTypeStr;
    }

    private String priceTypeStr;


    public double getDisCount() {
        return (double)this.finalPrice/this.price;
    }

    public void setDisCount(double disCount) {
        this.disCount = disCount;
    }

    //折扣
    private double disCount;

}
