package com.office.rebates.model.admin;

import java.util.Date;

/**
 * 返利订单模型
 */
public class RebatesOrderModel {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 销售id
     */
    private Long salesId;

    /**
     * soho的主要订单id
     */
    private Long soho3qOrderId;

    /**
     * 客户手机号
     */
    private String customerMobile;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户公司
     */
    private String customerCompany;

    /**
     * 客户支付宝账号
     */
    private String customerAlipay;

    /**
     * 项目id
     */
    private String porjectId;

    /**
     * 项目名称
     */
    private String porjectName;

    /**
     * 入驻时间
     */
    private Date checkinDate;

    /**
     * 离场时间
     */
    private Date checkoutDate;

    /**
     * 租期(月)
     */
    private Integer periodMonth;

    /**
     * 租期（周）
     */
    private Integer periodWeek;

    /**
     * 租金
     */
    private Integer leaseAmount;

    /**
     * 租金
     */
    private Integer depositAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    /**
     * 销售名称
     */
    private String userName;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 订单状态
     */
    private Byte status;

    /**
     * 税后佣金
     */
    private Integer bonusAfterTax;

    /**
     * 返利金额
     */
    private Integer rebatesAmount;

    public Integer getBonusAfterTax() {
        return bonusAfterTax;
    }

    public void setBonusAfterTax(Integer bonusAfterTax) {
        this.bonusAfterTax = bonusAfterTax;
    }

    public Integer getRebatesAmount() {
        return rebatesAmount;
    }

    public void setRebatesAmount(Integer rebatesAmount) {
        this.rebatesAmount = rebatesAmount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取销售名称
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置销售名称
     * @param userName 销售名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户名称
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名称
     * @param name 用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取订单id
     * @return
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取用户id
     * @return
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取销售id
     * @return
     */
    public Long getSalesId() {
        return salesId;
    }

    /**
     * 设置销售id
     * @param salesId 销售id
     */
    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    /**
     * 获取soho订单id
     * @return
     */
    public Long getSoho3qOrderId() {
        return soho3qOrderId;
    }

    /**
     * 设置soho订单id
     * @param soho3qOrderId soho订单id
     */
    public void setSoho3qOrderId(Long soho3qOrderId) {
        this.soho3qOrderId = soho3qOrderId;
    }

    /**
     * 获取客户手机号
     * @return
     */
    public String getCustomerMobile() {
        return customerMobile;
    }

    /**
     * 设置客户手机号
     * @param customerMobile 客户手机号
     */
    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile == null ? null : customerMobile.trim();
    }

    /**
     * 获取客户名称
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户名称
     * @param customerName 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * 获取客户公司
     * @return
     */
    public String getCustomerCompany() {
        return customerCompany;
    }

    /**
     * 设置客户公司
     * @param customerCompany 客户公司
     */
    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany == null ? null : customerCompany.trim();
    }

    /**
     * 获取客户支付宝账号
     * @return
     */
    public String getCustomerAlipay() {
        return customerAlipay;
    }

    /**
     * 设置客户支付宝账号
     * @param customerAlipay 客户支付宝账号
     */
    public void setCustomerAlipay(String customerAlipay) {
        this.customerAlipay = customerAlipay == null ? null : customerAlipay.trim();
    }

    /**
     * 获取项目id
     * @return
     */
    public String getPorjectId() {
        return porjectId;
    }

    /**
     * 设置项目id
     * @param porjectId 项目id
     */
    public void setPorjectId(String porjectId) {
        this.porjectId = porjectId == null ? null : porjectId.trim();
    }

    /**
     * 获取项目名称
     * @return
     */
    public String getPorjectName() {
        return porjectName;
    }

    /**
     * 设置项目名称
     * @param porjectName 项目名称
     */
    public void setPorjectName(String porjectName) {
        this.porjectName = porjectName == null ? null : porjectName.trim();
    }

    /**
     * 获取入驻时间
     * @return
     */
    public Date getCheckinDate() {
        return checkinDate;
    }

    /**
     * 设置入驻时间
     * @param checkinDate 入驻时间
     */
    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    /**
     * 获取离场时间
     * @return
     */
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * 设置离场时间
     * @param checkoutDate 离场时间
     */
    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * 获取租期（月）
     * @return
     */
    public Integer getPeriodMonth() {
        return periodMonth;
    }

    /**
     * 设置租期（月）
     * @param periodMonth 租期（月）
     */
    public void setPeriodMonth(Integer periodMonth) {
        this.periodMonth = periodMonth;
    }

    /**
     * 获取租期（周）
     * @return
     */
    public Integer getPeriodWeek() {
        return periodWeek;
    }

    /**
     * 设置租期（周）
     * @param periodWeek 租期（周）
     */
    public void setPeriodWeek(Integer periodWeek) {
        this.periodWeek = periodWeek;
    }

    /**
     * 获取租金
     * @return
     */
    public Integer getLeaseAmount() {
        return leaseAmount;
    }

    /**
     * 设置租金
     * @param leaseAmount 租金
     */
    public void setLeaseAmount(Integer leaseAmount) {
        this.leaseAmount = leaseAmount;
    }

    /**
     * 获取押金
     * @return
     */
    public Integer getDepositAmount() {
        return depositAmount;
    }

    /**
     * 设置押金
     * @param depositAmount 押金
     */
    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    /**
     * 获取创建时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后更新时间
     * @return
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置最后更新时间
     * @param lastUpdateTime 最后更新时间
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}