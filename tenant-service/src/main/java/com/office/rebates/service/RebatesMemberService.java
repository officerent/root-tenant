package com.office.rebates.service;

import com.office.rebates.model.request.SohoMemberInfo;

/**
 * Created by liyongfeng on 16/7/25.
 */
public interface RebatesMemberService {

    public SohoMemberInfo isMember(String mobile) throws Exception;
}
