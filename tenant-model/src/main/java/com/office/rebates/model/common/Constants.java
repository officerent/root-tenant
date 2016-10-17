package com.office.rebates.model.common;

import java.math.BigDecimal;

public class Constants {
	public static String LANG_CN = "zh_CN";
	public static String LANG_EN = "en_US";

	public static String SYSTEM = "SYSTEM";
	
	//rebates bonus status
	public static Byte BONUS_STATUS_ORDER_PLACED = 0;//需求单待确认
	public static Byte BONUS_STATUS_ORDER_CONFIRM = 1; //需求单已确认
	public static Byte BONUS_STATUS_ORDER_PAID = 2; //客户已支付订单
	public static Byte BONUS_STATUS_BONUS_PAID = 3;  //销售已收到佣金
	public static Byte BONUS_STATUS_REBATES_PAID = 4; //客户已经收到返利
	public static Byte BONUS_STATUS_ORDER_CANCELED = 5; //需求单已取消

	//是否是管理员
	public static Byte IS_ADMIN_Y = 1;
	public static Byte IS_ADMIN_N = 0;

	//用户状态
	public static Byte STATUS_NORMAL = 0;
	public static Byte STATUS_FROZEN = 1;
	public static Byte STATUS_DELETE = 2;

	//cookie
	public static String COOKIE_USER_TOKEN = "user_token";
	public static String COOKIE_USER_NAME = "user_name";
	
	public static String SOHO3Q_USER_TOKEN = "token";
	public static String SOHO3Q_SID = "sid";
	
	//user admin
	public static Byte USER_NOT_ADMIN = 0;//不是管理员
	public static Byte USER_ADMIN = 1; //是管理员
	
	//user status
	public static Byte USER_STATUS_NORMAL = 0;//正常
	public static Byte USER_STATUS_FROZEN = 1; //冻结
	public static Byte USER_STATUS_DELETED = 2; //删除
	
    public static String CONPON_TYPE_STANDARD = "STANDARD";//普通卷
    public static String CONPON_TYPE_MEMBERSHIP = "MEMBERSHIP";//浮动会员卷
    
    public static String PRODUCT_TYPE_ROOM = "ROOM";
    public static String PRODUCT_TYPE_OPEN_STATION = "OPEN_STATION";
	public static String COMMON_NOT_REFUNDABLE = "COMMON_NOT_REFUNDABLE";
    
    public static String DESCRIPTION_MEMBERSHIP = "99元的漫游会员身份是您购买和使用所有漫游权益的先决条件。第一次购买SOHO3Q的漫游产品，必须首先支付99元成为SOHO3Q的会员。成为会员的同时，还能赠送一天开放工位或者1小时会议室使用权";
    public static String DESCRIPTION_OPEN_STATION = "全国所有SOHO3Q都提供以天为单位漫游开放工位服务；您购买权益以后，可以随时预约任意的SOHO3Q的工位，实现真正的全国漫游办公，非常适合需要移动办公的上班族和创业者。";
    public static String DESCRIPTION_ROOM = "开会，搞培训，商务会谈，电话会议临时找不到地方？来SOHO3Q吧！这里的会议室高端大气上档次，提供所有您可能需要的服务和设施。";

	public static BigDecimal D0 = new BigDecimal(0);
	
	public static String LOCK_PULL_SOHO3Q_ORDERS = "PULL_SOHO3Q_ORDERS";//全局锁
	
    public static String ARTICLE_TYPE_PROJ = "PROJ";//项目介绍
    public static String ARTICLE_TYPE_SOHO3Q = "SOHO3Q";//3q动态
    public static String ARTICLE_TYPE_OFFICE = "OFFICE";//联合办公
    public static String ARTICLE_TYPE_OTHER = "OTHER";//其他




}
