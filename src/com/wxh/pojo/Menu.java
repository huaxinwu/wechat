/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.pojo;

/**
 * 菜单对象：一级菜单3个(最多四个汉字)，二级菜单5个(最多七个汉字)
 * @author wxh
 * @version $Id: Menu.java, v 0.1 2018年2月8日 下午3:55:27 wxh Exp $
 */
public class Menu {
    /**
     * 一个菜单由很多菜单项组成
     */
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }

}
