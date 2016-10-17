package com.office.rebates.controller.admin;

import com.office.rebates.controller.RouteKey;
import com.office.rebates.dal.dataobj.RebatesUser;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Page;
import com.office.rebates.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 * Created by liyongfeng on 2015/12/31.
 */
@Controller
@RequestMapping(RouteKey.USER)
public class AdminUserController {

    /**
     * 用户服务
     */
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 添加用户
     */
    @RequestMapping(RouteKey.ADD_USER)
    public void add_user(){

    }

    /**
     * 保存用户信息至数据库
     * @return
     */
    @RequestMapping(RouteKey.SAVE_USER)
    public String save_user(RebatesUser rebatesUser,String isNotAdmin) {
        if("on".equalsIgnoreCase(isNotAdmin)){
            rebatesUser.setIsAdmin(Constants.IS_ADMIN_Y);
        }else{
            rebatesUser.setIsAdmin(Constants.IS_ADMIN_N);
        }
        adminUserService.insert(rebatesUser);
        return "redirect:user_list.html";
    }

    /**
     * 跳转至修改用户信息
     * @param userId 用户id
     */
    @RequestMapping(RouteKey.MODIFY_USER)
    public void modify_user(long userId,Model model){
        model.addAttribute("data",adminUserService.selectOne(userId));
    }

    /**
     * 修改用户信息并保存至数据库
     * @return
     */
    @RequestMapping(RouteKey.UPDATE_USER)
    public String update_user(RebatesUser rebatesUser,String isNotAdmin){
        if("on".equalsIgnoreCase(isNotAdmin)){
            rebatesUser.setIsAdmin(Constants.IS_ADMIN_Y);
        }else{
            rebatesUser.setIsAdmin(Constants.IS_ADMIN_N);
        }
        adminUserService.update(rebatesUser);
        return "redirect:user_list.html";
    }

    /**
     * 获取用户列表
     */
    @RequestMapping(RouteKey.USER_LIST)
    public void user_list(Page page,String name,Model model){
        model.addAttribute("data",adminUserService.selectList(page,name));
        model.addAttribute("page",page);
        model.addAttribute("name",name);
    }

    /**
     * 删除用户信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping(RouteKey.UPDATE_STATUS)
    public String updateStatus(long userId,int status){
        adminUserService.updateStatus(userId,status);
        return "redirect:user_list.html";
    }
}
