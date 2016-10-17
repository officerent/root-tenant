package com.office.rebates.model;


import java.util.List;

/**
 * Created by xxm on 15/9/14.
 */
public class Soho3qProjectModel {
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    private String cityId;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    private String cityName;


    private String projectId;

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    private String projectLocation;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;

    public String getLocalCoordinate() {
        return localCoordinate;
    }

    public void setLocalCoordinate(String localCoordinate) {
        this.localCoordinate = localCoordinate;
    }

    //经纬度
    private String localCoordinate;

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    private String projectContent;

    private String projectArea;

    public String getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(String projectArea) {
        this.projectArea = projectArea;
    }

    private String projectCode;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    private String managerId;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    private String projectManagerName;

    private String projectManagerEmail;

    public String getProjectManagerEmail() {
        return projectManagerEmail;
    }

    public void setProjectManagerEmail(String projectManagerEmail) {
        this.projectManagerEmail = projectManagerEmail;
    }

    private String projectManagerTel;

    public String getProjectManagerTel() {
        return projectManagerTel;
    }

    public void setProjectManagerTel(String projectManagerTel) {
        this.projectManagerTel = projectManagerTel;
    }

    public List<Soho3qProjectImageModel> getProjectLargeImgs() {
        return projectLargeImgs;
    }

    public void setProjectLargeImgs(List<Soho3qProjectImageModel> projectLargeImgs) {
        this.projectLargeImgs = projectLargeImgs;
    }

    private List<Soho3qProjectImageModel> projectLargeImgs;
    
    private List<Soho3qProjectImageModel> projectSmallImgs;
    
    public List<Soho3qProjectImageModel> getProjectSmallImgs() {
        return projectSmallImgs;
    }

    public void setProjectSmallImgs(List<Soho3qProjectImageModel> projectSmallImgs) {
        this.projectSmallImgs = projectSmallImgs;
    }

    private String price;
    public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice() {
        return price;
    }

    private int total;
    private int remains;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemains() {
        return remains;
    }

    public void setRemains(int remains) {
        this.remains = remains;
    }
}
