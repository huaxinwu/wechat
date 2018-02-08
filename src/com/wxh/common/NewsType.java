/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.common;

/**
 * 自定义枚举：定义成员变量、构造方法、成员变量setter和getter方法
 * 微信中图文消息类型：单图文消息、单图文消息---不含图片 、多图文消息、多图文消息---首条消息不含图片 、多图文消息---最后一条消息不含图片  
 * @author wxh
 * @version $Id: NewsType.java, v 0.1 2018年2月8日 上午11:32:59 wxh Exp $
 */
public enum NewsType {

    SINGLE("1", "单图文消息"),

    SINGLE_NOTPIC("2", "单图文消息不含图片"),

    MULTI("3", "多图文消息"),

    MULTI_FIRST_NOTPIC("4", "多图文消息首条消息不含图片"),

    MULTI_LAST_NOTPIC("5", "多图文消息最后一条消息不含图片 ");

    // 成员变量
    private String code;
    private String msg;

    /**
     *  构造方法，没有public修饰的
     * @param code
     * @param msg
     */
    NewsType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
