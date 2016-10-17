package com.office.rebates.service.web.impl;

import com.office.rebates.model.BonusModel;
import com.office.rebates.service.RebatesBonusService;
import com.office.rebates.service.web.WebIndexService;
import com.office.rebates.util.CoverUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyongfeng on 16/6/30.
 */
@Service
public class WebIndexServiceImpl implements WebIndexService {

    /**
     * 佣金服务
     */
    @Autowired
    private RebatesBonusService rebatesBonusService;
    /**
     * 获取获得佣金者的排行榜
     * @return
     */
    @Override
    public List<BonusModel> getBonusList() throws Exception{
        List<BonusModel> bonusModels = new ArrayList<>();
        bonusModels = rebatesBonusService.getRecentBonus();
        for(BonusModel bonusModel : bonusModels){
            bonusModel.setCustomerMobile(CoverUtils.coverEncryption(bonusModel.getCustomerMobile(),3,3));
        }
        return bonusModels;
    }
}
