package com.office.rebates.model.request;


import java.util.ArrayList;

/**
 * Created by qiuliang on 15/9/16.
 */
public class SelectProductRequest {
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private String source;

    public ArrayList<SelectProductModel> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<SelectProductModel> productList) {
        this.productList = productList;
    }

    private ArrayList<SelectProductModel> productList;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    private String startDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private String endDate;

    public int getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(int weekCount) {
        this.weekCount = weekCount;
    }

    private int weekCount;

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    private int monthCount;
}
