/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.pojo;

/**
 * 菜单项封装：按钮的基类 
 *   {  
 *       "button":[  
 *       {     
 *            "type":"click",  
 *            "name":"今日歌曲",  
 *            "key":"V1001_TODAY_MUSIC"  
 *        },  
 *        {  
 *             "type":"click",  
 *             "name":"歌手简介",  
 *             "key":"V1001_TODAY_SINGER"  
 *        },  
 *        {  
 *             "name":"菜单",  
 *             "sub_button":[  
 *              {  
 *                 "type":"click",  
 *                 "name":"hello word",  
 *                 "key":"V1001_HELLO_WORLD"  
 *              },  
 *              {  
 *                 "type":"click",  
 *                 "name":"赞一下我们",  
 *                 "key":"V1001_GOOD"  
 *              }]  
 *         }]  
 *   }
 *   
 *   
 * @author wxh
 * @version $Id: Button.java, v 0.1 2018年2月8日 下午3:44:26 wxh Exp $
 */
public class Button {

    /**
     * 菜单项名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
