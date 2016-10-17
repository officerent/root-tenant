package com.office.rebates.service.web;

import com.office.rebates.model.BonusModel;

import java.util.List;

/**
 * 首页
 * Created by liyongfeng on 16/6/30.
 */
public interface WebIndexService {

    public List<BonusModel> getBonusList() throws Exception;
}
