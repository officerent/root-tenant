package com.office.rebates.model.request;

/**
 * Created by liyongfeng on 16/6/2.
 */
public class SohoMemberInfo {

	private Boolean isMember;
	private String memberStartDate;
	private String memberEndDate;
	public Boolean getIsMember() {
		return isMember;
	}
	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}
	public Boolean getIsFloatMember() {
		return isFloatMember;
	}
	public void setIsFloatMember(Boolean isFloatMember) {
		this.isFloatMember = isFloatMember;
	}
	public Boolean getIsFixMember() {
		return isFixMember;
	}
	public void setIsFixMember(Boolean isFixMember) {
		this.isFixMember = isFixMember;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMemberStartDate() {
		return memberStartDate;
	}
	public void setMemberStartDate(String memberStartDate) {
		this.memberStartDate = memberStartDate;
	}
	public String getMemberEndDate() {
		return memberEndDate;
	}
	public void setMemberEndDate(String memberEndDate) {
		this.memberEndDate = memberEndDate;
	}
	public String getFloatMemberStartDate() {
		return floatMemberStartDate;
	}
	public void setFloatMemberStartDate(String floatMemberStartDate) {
		this.floatMemberStartDate = floatMemberStartDate;
	}
	public String getFloatMemberEndDate() {
		return floatMemberEndDate;
	}
	public void setFloatMemberEndDate(String floatMemberEndDate) {
		this.floatMemberEndDate = floatMemberEndDate;
	}
	public String getFixMemberStartDate() {
		return fixMemberStartDate;
	}
	public void setFixMemberStartDate(String fixMemberStartDate) {
		this.fixMemberStartDate = fixMemberStartDate;
	}
	public String getFixMemberEndDate() {
		return fixMemberEndDate;
	}
	public void setFixMemberEndDate(String fixMemberEndDate) {
		this.fixMemberEndDate = fixMemberEndDate;
	}

	private Boolean isFloatMember;
	private Boolean isFixMember;
	private String userId;
	private String projectName;

	private String floatMemberStartDate;
	private String floatMemberEndDate;

	private String fixMemberStartDate;
	private String fixMemberEndDate;
}
