/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.pojo;

/**
 * 普通按钮(子按钮) :菜单名称、菜单类型(点击或跳转等等)、菜单键值(key)
 * @author wxh
 * @version $Id: CommonButton.java, v 0.1 2018年2月8日 下午3:46:07 wxh Exp $
 */
public class CommonButton extends Button {
    /**
     * 菜单项类型：click/view etc.
     */
    private String type;

    /**
     * 菜单项键值：key="V1001_GOOD"
     */
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
