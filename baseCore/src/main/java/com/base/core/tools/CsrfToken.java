package com.base.core.tools;


import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;


public class CsrfToken {
    public static final String DEFAULT_TOKEN_KEY = "x-csrf-token";
    public static final int DEFAULT_MAX_TOKENS = 8;
    public static final String CSRF_TOKEN_SEPARATOR = "/";

    /**
     * 获取csrfToken储存主键
     *
     * @return 返回储存主键
     */
    public static String getKey() {
        return DEFAULT_TOKEN_KEY;
    }

    /**
     * 获取允许的最大token数量
     *
     * @return 返回最大token数量
     */
    public static int getMaxTokens() {
         
        return DEFAULT_MAX_TOKENS;
    }

    /**
     * 设置csrfToken字符串
     *
     * @return 返回csrfToken字符串
     */
    public static String setCsrfTokenInSession() {
        String token = generateCsrfToken();
        setCsrfTokensInSession(token);
         return token;
    }

    /**
     * 生成MD5加密 返回十六进制的字符串
     *
     * @return 返回加密后的csrfToken字符串
     */
    public static String generateCsrfToken() {
      /*  HttpSession session = BaseTools.getRequest().getSession();
        return DigestUtils.md5Hex(session.getId()
                + session.getCreationTime());*/
        
        return UUID.randomUUID().toString();
    }

    /**
     * 查询当前的csrfToken值
     *
     * @return 返回当前的值
     */
    public static String getCsrfTokenInSession() {
    	return (String) BaseTools.getRequest().getSession().getAttribute(getKey());
    }

    /**
     * 在session中保存csrfToken(token的寿命和session相同)
     *
     * @param csrfToken token
     */
    public static void setCsrfTokensInSession(String csrfToken) {
    	BaseTools.getRequest().getSession().setAttribute(getKey(), csrfToken);
    }

    public static void removeCsrfTokenInSession() {
    	BaseTools.getRequest().getSession().removeAttribute(getKey());
    }

    /**
     * 从session中获取tokens列表(token的寿命和session相同)
     *
     * @return 返回tokens列表
     */
    public static String getCsrfTokensInSession() {
        return (String) BaseTools.getRequest().getSession().getAttribute(getKey());
    }
}
