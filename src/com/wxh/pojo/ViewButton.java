/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.pojo;

/**
 * 跳转按钮
 * View类型的菜单有3个属性：type、name和url
 * @author wxh
 * @version $Id: ViewButton.java, v 0.1 2018年2月8日 下午5:10:06 wxh Exp $
 */
public class ViewButton extends Button {

    /**
     * 菜单项类型
     */
    private String type;

    /**
     * 菜单项跳转链接
     */
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
