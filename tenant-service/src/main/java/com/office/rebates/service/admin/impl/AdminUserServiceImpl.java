package com.office.rebates.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.office.rebates.dal.dao.NewRebatesUserMapper;
import com.office.rebates.dal.dao.RebatesUserMapper;
import com.office.rebates.dal.dataobj.RebatesUser;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Page;
import com.office.rebates.service.admin.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户服务实现类
 * Created by liyongfeng on 2016/1/3.
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    /**
     * 日志
     */
    private  Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    /**
     * 用户dao
     */
    @Autowired
    private RebatesUserMapper rebatesUserMapper;

    /**
     * 新用户dao
     */
    @Autowired
    private NewRebatesUserMapper newRebatesUserMapper;

    /**
     * 插入数据库
     * @param rebatesUser 用户实体
     * @return
     */
    @Override
    public int insert(RebatesUser rebatesUser) {
        if(StringUtils.isBlank(rebatesUser.getName())){
            logger.info("user name is null");
            return 0;
        }
        if(StringUtils.isBlank(rebatesUser.getPassword())){
            logger.info("user password is null");
            return 0;
        }
        Date date = new Date();
        rebatesUser.setCreateTime(date);
        rebatesUser.setLastFrozenTime(date);
        rebatesUser.setLastUpdateTime(date);
        rebatesUser.setStatus(Constants.STATUS_NORMAL);
        try{
            rebatesUserMapper.insert(rebatesUser);
        }catch (Exception e){
            logger.error("insert user have a error param:rebatesUser"+ JSON.toJSONString(rebatesUser),e);
        }
        return 0;
    }

    /**
     * 删除或者冻结用户
     * @param userId 用户id
     * @return
     */
    @Override
    public int updateStatus(long userId,int status) {
        try{
            RebatesUser rebatesUser = rebatesUserMapper.selectByPrimaryKey(userId);
            Date date = new Date();
            if(status == 1){
                rebatesUser.setLastFrozenTime(date);
            }
            rebatesUser.setLastUpdateTime(date);
            rebatesUser.setStatus((byte) status);
            rebatesUserMapper.updateByPrimaryKey(rebatesUser);
        }catch (Exception e){
            logger.error("delete or frozen have a error param: userId = %s ,status = %s",userId,status,e);
        }
        return 0;
    }

    /**
     * 修改用户信息
     * @param rebatesUser 用户信息载体
     * @return
     */
    @Override
    public int update(RebatesUser rebatesUser) {
        try{
            RebatesUser rebates = rebatesUserMapper.selectByPrimaryKey(rebatesUser.getUserId());
            if(StringUtils.isBlank(rebatesUser.getName())){
                logger.info("user name is null");
                return 0;
            }else{
                rebates.setName(rebatesUser.getName());
            }
            if(StringUtils.isBlank(rebatesUser.getPassword())){
                logger.info("user password is null");
                return 0;
            }else{
                rebates.setPassword(rebatesUser.getPassword());
            }
            rebates.setIsAdmin(rebatesUser.getIsAdmin());
            Date date = new Date();
            rebates.setLastUpdateTime(date);
            rebatesUserMapper.updateByPrimaryKey(rebates);
        }catch (Exception e){
            logger.error("delete or frozen have a error param:"+ JSON.toJSONString(rebatesUser),e);
        }
        return 0;
    }

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return
     */
    @Override
    public RebatesUser selectOne(long userId) {
        RebatesUser rebates =null;
        try{
            rebates = rebatesUserMapper.selectByPrimaryKey(userId);
        }catch (Exception e){
            logger.error("delete or frozen have a error param:userId = %s",userId,e);
        }
        return rebates;
    }

    /**
     * 查询用户信息列表
     * @param page 分页
     * @param name 用户名称
     * @return
     */
    @Override
    public List<RebatesUser> selectList(Page page, String name) {
        List<RebatesUser> rebatesUsers = new ArrayList<RebatesUser>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        try{
            page.setTotalElements(newRebatesUserMapper.countUserList(map));
            map.put("start",page.getNumber()*page.getSize());
            map.put("size",page.getSize());
            rebatesUsers = newRebatesUserMapper.selectUserList(map);
        }catch (Exception e){
            logger.error("select user list param: page = %s , name = $s", JSON.toJSONString(page),name,e);
        }
        return rebatesUsers;
    }
}
