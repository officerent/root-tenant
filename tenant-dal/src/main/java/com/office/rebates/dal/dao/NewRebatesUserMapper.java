package com.office.rebates.dal.dao;

import com.office.rebates.dal.dataobj.RebatesUser;
import com.office.rebates.dal.dataobj.RebatesUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 新用户dao
 */
public interface NewRebatesUserMapper {

    /**
     * 查询用户列表
     * @param map 条件
     * @return
     */
    public List<RebatesUser> selectUserList(Map<String,Object> map);

    /**
     * 计算用户数
     * @param map 条件
     * @return
     */
    public Integer countUserList(Map<String,Object> map);
}