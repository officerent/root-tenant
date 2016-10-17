package com.office.rebates.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.jxpath.ri.compiler.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.Constants;
import com.office.rebates.model.CreateOrderResult;
import com.office.rebates.model.OrderModel;
import com.office.rebates.model.UserInfo;
import com.office.rebates.model.common.ListResult;
import com.office.rebates.model.common.Messages;
import com.office.rebates.model.common.RebatesException;
import com.office.rebates.model.common.ResultCode;
import com.office.rebates.model.request.CreateCouponOrderRequest;
import com.office.rebates.model.request.CreateOrderRequest;
import com.office.rebates.service.RebatesOrderService;
import com.office.rebates.service.UserService;


@Controller
@RequestMapping(RouteKey.PREFIX_AJAX+"/"+RouteKey.REBATES_ORDER)
public class RebatesOrderController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private RebatesOrderService rebatesOrderService;
    
    @Autowired
    private RebatesOrderService rebatesCouponOrderService;
    
    static Logger logger = LoggerFactory.getLogger(RebatesOrderController.class);
	
    //创建一个新的订单
    @ResponseBody
	@RequestMapping(value = RouteKey.CREATE_ORDER, method = RequestMethod.POST)
	public ResultCode<CreateOrderResult> createRebatesOrder(@RequestBody CreateOrderRequest request,HttpServletRequest httpServletRequest) {
		ResultCode<CreateOrderResult> result=new ResultCode<CreateOrderResult>();		
		//check params
		if(request==null){
			result.setErrCode(Messages.REQUEST_NULL_CODE);
			result.setErrMsg(Messages.REQUEST_NULL_MSG);
			return result;
		}
		
		if(request.getCheckInDate()==null||
			request.getCheckOutDate()==null||
			request.getCustomerAlipay()==null||
			//request.getCustomerCompany()==null||
			request.getCustomerMobile()==null||
			request.getCustomerName()==null||
			request.getDepositAmount()==null||
			request.getLeaseAmount()==null||
			request.getOrderItems()==null||
			request.getOrderItems().isEmpty()||
			request.getPeriodMonth()==null||
			request.getPeriodWeek()==null||
			request.getProjectId()==null||
			request.getProjectName()==null)
			{
				result.setErrCode(Messages.MISSING_REQUIRED_PARAM_CODE);
				result.setErrMsg(Messages.MISSING_REQUIRED_PARAM_MSG);
				return result;
			}
		if(request.getCustomerCompany()==null){
			request.setCustomerCompany(request.getCustomerName());
		}
		//check if the user is logon
		Cookie[] cookies=httpServletRequest.getCookies();
		logger.info("creating order with parms:"+JSON.toJSONString(request)+",cookies:"+JSON.toJSONString(cookies));
		UserInfo userInfo=userService.getUserInfo(cookies);
		logger.info("user info is:"+JSON.toJSONString(userInfo));
		if(userInfo==null||userInfo.getUserId()==null){
			result.setErrCode(Messages.USER_NOT_LOGON_CODE);
			result.setErrMsg(Messages.USER_NOT_LOGON_MSG);
			return result;
		}
		
		//create the order
		try {
			CreateOrderResult createOrderResult=rebatesOrderService.createRebatesOrder(request,userInfo);
			result.setData(createOrderResult);
		} catch (RebatesException e) {
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}
		return result;

	}
    
    //创建一个新的买券订单
    @ResponseBody
	@RequestMapping(value = RouteKey.CREATE_COUPON_ORDER, method = RequestMethod.POST)
	public ResultCode<CreateOrderResult> createRebatesCouponOrder(@RequestBody CreateCouponOrderRequest request,HttpServletRequest httpServletRequest) {
		ResultCode<CreateOrderResult> result=new ResultCode<CreateOrderResult>();		
		//check params
		if(request==null){
			result.setErrCode(Messages.REQUEST_NULL_CODE);
			result.setErrMsg(Messages.REQUEST_NULL_MSG);
			return result;
		}
		
		if(
			request.getCustomerAlipay()==null||
			request.getCustomerCompany()==null||
			request.getCustomerMobile()==null||
			//request.getCustomerName()==null||
			request.getCouponOrderItems()==null||
			request.getCouponOrderItems().isEmpty())
			{
				result.setErrCode(Messages.MISSING_REQUIRED_PARAM_CODE);
				result.setErrMsg(Messages.MISSING_REQUIRED_PARAM_MSG);
				return result;
			}
		
		if(request.getCustomerCompany()==null){
			request.setCustomerCompany(request.getCustomerName());
		}
		
		//check if the user is logon
		Cookie[] cookies=httpServletRequest.getCookies();
		logger.info("creating order with parms:"+JSON.toJSONString(request)+",cookies:"+JSON.toJSONString(cookies));
		UserInfo userInfo=userService.getUserInfo(cookies);
		logger.info("user info is:"+JSON.toJSONString(userInfo));
		if(userInfo==null||userInfo.getUserId()==null){
			result.setErrCode(Messages.USER_NOT_LOGON_CODE);
			result.setErrMsg(Messages.USER_NOT_LOGON_MSG);
			return result;
		}
		
		//create the order
		try {
			CreateOrderResult createOrderResult=rebatesCouponOrderService.createRebatesCouponOrder(request,userInfo);
			result.setData(createOrderResult);
		} catch (RebatesException e) {
			result.setErrCode(e.getErrCode());
			result.setErrMsg(e.getErrMsg());
		}
		return result;

	}
    
    //获取我的订单列表
    @ResponseBody
	@RequestMapping(value = RouteKey.MY_LIST, method = RequestMethod.GET)
	public ResultCode<ListResult<OrderModel>> getMyOrderList(Integer pageSize,Integer pageNum,HttpServletRequest httpServletRequest) {
		ResultCode<ListResult<OrderModel>> result=new ResultCode<ListResult<OrderModel>>();		
		//check params
		if(pageSize==null||pageSize<=0){
			pageSize=20;
		}
		if(pageNum==null||pageNum<0){
			pageNum=0;
		}
		
		//check if the user is logon
		Cookie[] cookies=httpServletRequest.getCookies();
		UserInfo userInfo=userService.getUserInfo(cookies);
		//logger.info("user info is:"+JSON.toJSONString(userInfo));
		if(userInfo==null||userInfo.getUserId()==null){
			result.setErrCode(Messages.USER_NOT_LOGON_CODE);
			result.setErrMsg(Messages.USER_NOT_LOGON_MSG);
			return result;
		}
		
		//get the order list
		try {
			Integer totalNum=rebatesOrderService.getMyOrderNum(userInfo.getUserId());;
			List<OrderModel> orders=rebatesOrderService.getMyOrders(userInfo.getUserId(),pageSize,pageNum);
			ListResult<OrderModel> resultList=new ListResult<OrderModel>();
			resultList.setListData(orders);
			resultList.setPageNum(pageNum);
			resultList.setPageSize(pageSize);
			resultList.setTotalNum(totalNum);
			result.setData(resultList);
		} catch (Exception e) {
			logger.info("fail to get my order list",e);
			result.setErrCode(Messages.FAIL_TO_GET_MYORDER_LIST_CODE);
			result.setErrMsg(Messages.FAIL_TO_GET_MYORDER_LIST_MSG);
		}
		return result;

	}
}
