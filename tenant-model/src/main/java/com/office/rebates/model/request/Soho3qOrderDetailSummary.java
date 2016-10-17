package com.office.rebates.model.request;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by qiuliang on 15/9/17.
 * summary 展示里的每一行数据模型
 */
public class Soho3qOrderDetailSummary {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    private String title;

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    private Long unitPrice;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private Integer quantity;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    private Long price;

    /**
     * 排序号
     */
    private int sortNo;

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
}
