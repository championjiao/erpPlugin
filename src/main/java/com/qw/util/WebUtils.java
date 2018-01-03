/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qw.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 返回服务器所在域名
 * @author Sanlion.Do
 */
public class WebUtils {

    /**
     * 返回域名：形如 http://api-m.pre.qw.com:80/
     *
     * @param request
     * @return
     */
    public static String getServerDomain(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
    }

    /**
     * 返回域名：形如 http://api-m.pre.qw.com
     *
     * @param request
     * @return
     */
    public static String getServerDomainWithoutPort(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName();
    }

    /**
     * 返回顶级域名：形如 qw.com
     *
     * @param request
     * @return
     */
    public static String getServerTopDomain(HttpServletRequest request) {
        String domain = getServerDomainWithoutPort(request);
        String[] split = domain.split("[.]");
        return split[split.length - 2] + "." + split[split.length - 1];
    }

    public static String getIp(HttpServletRequest request) {
        return request.getHeader("x-forwarded-for") == null ? request.getRemoteAddr() : request.getHeader("x-forwarded-for");
    }
}
