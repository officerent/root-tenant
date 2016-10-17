package com.office.rebates.service.impl;

import com.office.rebates.dal.rest.Soho3qMemberApi;
import com.office.rebates.model.request.SohoMemberInfo;
import com.office.rebates.service.RebatesMemberService;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyongfeng on 16/7/25.
 */
@Service
public class RebatesMemberServiceImpl implements RebatesMemberService {

    /**
     * 用户信息api
     */
    @Autowired
    private Soho3qMemberApi soho3qMemberApi;

    /**
     * 获取用户信息服务
     * @param mobile 手机号
     * @return
     */
    @Override
    public SohoMemberInfo isMember(String mobile) throws Exception{
        SohoMemberInfo memberInfo = new SohoMemberInfo();
        if(StringUtils.isNotBlank(mobile)){
            memberInfo = soho3qMemberApi.getMemberInfo(mobile);
        }
        return memberInfo;
    }
}
