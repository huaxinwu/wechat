/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxh.pojo.AccessToken;
import com.wxh.pojo.Button;
import com.wxh.pojo.CommonButton;
import com.wxh.pojo.ComplexButton;
import com.wxh.pojo.Menu;
import com.wxh.pojo.ViewButton;
import com.wxh.util.WeiXinUtil;

/**
 * 菜单管理器类
 * @author wxh
 * @version $Id: MenuManager.java, v 0.1 2018年2月8日 下午4:21:59 wxh Exp $
 */
public class MenuManager {

    /** 记录该类的日志   */
    private static final Logger logger = LoggerFactory.getLogger(MenuManager.class);

    /**
     * 创建菜单程序入口
     * @param args
     */
    public static void main(String[] args) {
        // 在微信公众平台配置AppID
        String appId = "wx322678300e1f3953";
        // 在微信公众平台配置AppSecret
        String appSecret = "1e48f35b423defb91f7a48c497d4434a";

        // 调用接口获取access_token  
        AccessToken accessToken = WeiXinUtil.getAccessToken(appId, appSecret);
        // 如果请求成功
        if (null != accessToken) {
            // 调用接口创建菜单 
            int result = WeiXinUtil.createMenu(getMenu(), accessToken.getToken());
            // 判断菜单创建结果  
            if (0 == result) {
                logger.info("创建菜单成功！");
            } else {
                logger.info("创建菜单失败，错误码：" + result);
            }
        }
    }

    /**
     * 组装菜单数据 ，构建菜单对象
     * @return Menu
     */
    public static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("天气预报");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("周边搜索");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("历史上的今天");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn21 = new CommonButton();
        btn21.setName("歌曲点播");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("经典游戏");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("美女电台");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("人脸识别");
        btn24.setType("click");
        btn24.setKey("24");

        CommonButton btn25 = new CommonButton();
        btn25.setName("聊天唠嗑");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("Q友圈");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("电影排行榜");
        btn32.setType("click");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("幽默笑话");
        btn33.setType("click");
        btn33.setKey("33");

        CommonButton btn34 = new CommonButton();
        btn34.setName("关于我们");
        btn34.setType("click");
        btn34.setKey("35");

        ViewButton btn35 = new ViewButton();
        btn35.setName("使用帮助");
        btn35.setType("view");
        btn35.setUrl("http://liufeng.gotoip2.com/xiaoqrobot/help.jsp");

        // 添加二级菜单
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("休闲驿站");
        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn34, btn35 });

        /** 
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
         */
        Menu menu = new Menu();
        // 设置菜单的多个菜单项
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;

    }

}
