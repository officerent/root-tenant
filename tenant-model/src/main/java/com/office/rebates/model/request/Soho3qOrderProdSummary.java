package com.office.rebates.model.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

/**
 * Created by qiuliang
 * 订单填写页改summary展示
 */
public class Soho3qOrderProdSummary {
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    private String projectId;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;

    public ArrayList<Soho3qOrderDetailSummary> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Soho3qOrderDetailSummary> productList) {
        this.productList = productList;
    }

    private ArrayList<Soho3qOrderDetailSummary> productList;

    //extend field

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    private String checkInDate;

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    private String checkOutDate;

    //租金，单位：分
    private long totalRent;

    public long getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(long totalRent) {
        this.totalRent = totalRent;
    }

    //保证金
    private long totalDeposit;

    public long getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(long totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    //佣金
    private long totalFee;

    public long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public long getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(long feeRate) {
        this.feeRate = feeRate;
    }

    //返佣率
    private long feeRate;

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    private String salesName;

    public String getSalesMobile() {
        return salesMobile;
    }

    public void setSalesMobile(String salesMobile) {
        this.salesMobile = salesMobile;
    }

    private String salesMobile;

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    /**
     * 工位总数
     */
    private Integer seatCount;

    /**
     * 工位ID字符串
     */
    private String workbenchIds;

    public String getWorkbenchIds() {
        return workbenchIds;
    }

    public void setWorkbenchIds(String workbenchIds) {
        this.workbenchIds = workbenchIds;
    }
}
