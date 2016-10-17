package com.office.rebates.model.request;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by qiuliang on 15/9/16.
 */
public class SelectProductModel {


    private Integer bookNum;


    public Integer getBookNum() {
		return bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    private String productType;
    private String productSubType;

    public String getProductSubType() {
        return productSubType;
    }

    public void setProductSubType(String productSubType) {
        this.productSubType = productSubType;
    }
}
