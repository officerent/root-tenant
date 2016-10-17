package com.office.rebates.service.admin;

import com.office.rebates.dal.dataobj.RebatesUser;
import com.office.rebates.model.common.Page;

import java.util.List;

/**
 * 后台用户服务接口
 * Created by liyongfeng on 2016/1/3.
 */
public interface AdminUserService {

    /**
     * 添加用户到数据库
     * @param rebatesUser 用户实体
     * @return
     */
    public int insert(RebatesUser rebatesUser);

    /**
     * 删除用户到数据库
     * @param userId 用户id
     * @return
     */
    public int updateStatus(long userId,int status);

    /**
     * 修改用户信息保存到数据库
     * @param rebatesUser 用户信息载体
     * @return
     */
    public int update(RebatesUser rebatesUser);

    /**
     * 从数据库中查询用户信息
     * @param userId 用户id
     * @return
     */
    public RebatesUser selectOne(long userId);

    /**
     * 从数据库中查询用户信息列表
     * @param page 分页
     * @return
     */
    public List<RebatesUser> selectList(Page page,String name);
}
