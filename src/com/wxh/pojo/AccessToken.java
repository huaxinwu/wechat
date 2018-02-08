/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.pojo;

/**
 * 微信通用接口凭证 类
 * 微信调用凭证接口，返回json格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}，进行封装
 * @author wxh
 * @version $Id: AccessToken.java, v 0.1 2018年2月8日 下午3:39:41 wxh Exp $
 */
public class AccessToken {

    /**
     * 获取到的凭证  
     */
    private String token;

    /**
     * 凭证有效时间，单位：秒 
     */
    private int    expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
