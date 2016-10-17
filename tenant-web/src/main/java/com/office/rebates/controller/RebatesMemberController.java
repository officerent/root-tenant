package com.office.rebates.controller;

import com.office.rebates.model.RebatesRatio;
import com.office.rebates.model.common.ResultCode;
import com.office.rebates.model.request.SohoMemberInfo;
import com.office.rebates.service.RebatesMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyongfeng on 16/7/25.
 */
@RestController
@RequestMapping(RouteKey.PREFIX_AJAX+"/"+RouteKey.REBATES_MEMBER)
public class RebatesMemberController {

    /**
     * 日志
     */
    static Logger logger = LoggerFactory.getLogger(RebatesMemberController.class);
    /**
     * 查询用户信息接口
     */
    @Autowired
    private RebatesMemberService rebatesMemberService;

    /**
     * 查询用户信息
     * @param mobile 手机号
     */
    @RequestMapping(RouteKey.REBATES_MEMBER_INFO)
    public ResultCode<SohoMemberInfo> isMember(String mobile){
        ResultCode<SohoMemberInfo> result = new ResultCode<SohoMemberInfo>();
        logger.info("customer mobile" + mobile);
        try {
            SohoMemberInfo sohoMemberInfo = rebatesMemberService.isMember(mobile);
            result.setData(sohoMemberInfo);
        } catch (Exception e) {
            logger.error("RebatesMemberController.isMember",e);
        }
        return result;
    }
}
