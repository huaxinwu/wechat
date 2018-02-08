/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.pojo;

/**
 * 复杂按钮(父按钮):包含有二级菜单项的一级菜单
 * @author wxh
 * @version $Id: ComplexButton.java, v 0.1 2018年2月8日 下午3:52:34 wxh Exp $
 */
public class ComplexButton extends Button {

    /**
     * 子菜单项数组
     */
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }

}
