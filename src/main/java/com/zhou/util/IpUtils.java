package com.zhou.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author：周申宇
 * @Date:2021/4/6 11:28
 * @Decription:
 */
public class IpUtils {

    /**
     * 获取IP地址.
     *
     * @param request
     *   HTTP请求.
     * @param response
     *   HTTP响应.
     * @param url
     *   需转发到的URL.
     */
     public static String getIpAddr(HttpServletRequest request)
     {
     String ip = request.getHeader("x-forwarded-for");
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
     {
     ip = request.getHeader("Proxy-Client-IP");
     }
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
     {
     ip = request.getHeader("WL-Proxy-Client-IP");
     }
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
     {
     ip = request.getRemoteAddr();
     }
     return ip;
     }

}
