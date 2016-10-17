package com.office.rebates.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qiuliang on 15/8/17.
 */
public class CookieHelper {
    /*
    根据request cookie的请求顺序，返回匹配的第一个cookie，如果存在不同子域下的同名cookie，该方法可能会有问题
     */
    public static String getCookie(String key, HttpServletRequest request) {
        Cookie[] cks = request.getCookies();
        if(cks == null) return null;
        if(cks.length == 0) return null;
        for(Cookie ck : cks) {
            if(ck.getName().equals(key)) return ck.getValue();
        }
        return null;
    }

    public static void setCookie(String name, String value, String domain, String path, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static boolean hasCookie(Cookie[] cks, String name) {
        if(cks == null) return false;
        if(cks.length == 0) return false;
        for(Cookie ck : cks) {
            if(ck.getName() != null && ck.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
