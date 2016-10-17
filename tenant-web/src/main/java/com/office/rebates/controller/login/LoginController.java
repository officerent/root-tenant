package com.office.rebates.controller.login;

import com.office.rebates.controller.RouteKey;
import com.office.rebates.model.common.Constants;
import com.office.rebates.util.CookieHelper;
import com.office.rebates.util.PropertiesUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器
 * Created by liyongfeng on 2016/1/24.
 */
@Controller
@RequestMapping(RouteKey.REBATES_USER)
public class LoginController {

    /**
     * 跳转至登录接口
     */
    @RequestMapping(RouteKey.DO_LOGOUT)
    public String logout(HttpServletResponse response){
        //set user_token
        CookieHelper.setCookie(
                Constants.COOKIE_USER_TOKEN
                , ""
                , PropertiesUtils.prop.get("domain.name")
                , "/"
                , 0  //cookie有效期0天
                , response
        );
        //set user_name
        CookieHelper.setCookie(
                Constants.COOKIE_USER_NAME
                , ""
                , PropertiesUtils.prop.get("domain.name")
                , "/"
                , 0  //cookie有效期0天
                , response
        );
        return "redirect:login.html";
    }

    @RequestMapping(RouteKey.DO_LOGIN)
    public void login(){
    }
}
