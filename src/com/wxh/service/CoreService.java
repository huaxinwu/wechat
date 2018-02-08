/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxh.common.Constant;
import com.wxh.message.response.Article;
import com.wxh.message.response.NewsMessage;
import com.wxh.message.response.TextMessage;
import com.wxh.util.MessageUtil;
import com.wxh.util.QqFaceUtil;

/**
 * 核心业务类接收微信服务器发来的消息、处理微信服务器发来的消息 
 * @author wxh
 * @version $Id: CoreService.java, v 0.1 2018年2月7日 下午3:08:13 wxh Exp $
 */
public class CoreService {

    /**
     * 处理微信发来的请求 
     * 所有的请求数据都封装在request对象中
     * @param request
     * @return String
     */
    public static String processRequest(HttpServletRequest request) {
        // 接收处理结果
        String respMessage = null;
        try {
            // 默认返回的文本消息内容 
            String respContent = "请求处理异常，请稍候尝试！";

            // 解析xml转换为Map
            Map<String, String> requestMap = MessageUtil.parseXML(request);

            // 发送方帐号（open_id）---在回复消息中，它是接收消息方，与请求消息相反
            String fromUserName = requestMap.get("FromUserName");
            // 微信公众帐号 
            String toUserName = requestMap.get("ToUserName");
            // 消息类型  
            String msgType = requestMap.get("MsgType");

            // 根据请求消息类型，回复不同消息
            if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {

                respContent = "您发送的是文本消息！";

                // 文本消息内容
                respContent = requestMap.get("Content");

                // 判断用户发送的是否是单个QQ表情 
                if (QqFaceUtil.isQqFace(respContent)) {
                    // 回复文本消息  
                    TextMessage textMessage = new TextMessage();
                    // 回复给谁的消息
                    textMessage.setToUserName(fromUserName);
                    // 谁回复用户的请求消息
                    textMessage.setFromUserName(toUserName);
                    // 回复消息的时间
                    textMessage.setCreateTime(new Date().getTime());
                    // 回复消息的类型--响应之文本消息
                    textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                    // 回复消息星标标识,零表示可以再提交
                    textMessage.setFuncFlag(0);
                    // 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义  
                    textMessage
                        .setContent("欢迎访问<a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>!");

                    // 用户发什么QQ表情，就返回什么QQ表情  
                    textMessage.setContent(respContent);

                    // 设置Unified版本符号表情 emoji，需要转换0x--U+
                    textMessage.setContent("自行车" + QqFaceUtil.emoji(0x1F6B2) + "男性"
                                           + QqFaceUtil.emoji(0x1F6B9) + " 钱袋"
                                           + QqFaceUtil.emoji(0x1F4B0));

                    // 设置SoftBank版本符号表情 emoji，无需转换
                    textMessage.setContent("自行车\ue136 男人\ue138 钱袋\ue12f 情侣\ue428 公共汽车\ue159");

                    // 将响应消息之文本消息对象转换为xml字符串
                    respMessage = MessageUtil.textMessageToXML(textMessage);
                }

                //========================接收和回复图文消息========================================

                // 回复图文消息
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setFuncFlag(0);

                // 存储多个图文消息单项条目集合
                List<Article> articleList = new ArrayList<Article>();

                // 单图文消息
                if (Constant.SINGLE.equals(respContent)) {
                    // 创建图文消息单项条目类
                    Article article = new Article();
                    article.setTitle("微信公众帐号开发教程Java版");
                    article.setDescription("柳峰，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
                    article.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
                    article.setUrl("http://blog.csdn.net/lyq8479");
                    articleList.add(article);

                    // 设置图文消息个数 
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合 
                    newsMessage.setArticles(articleList);

                    // 将图文消息对象转换成xml字符串 
                    respMessage = MessageUtil.newsMessageToXML(newsMessage);

                } else if (Constant.SINGLE_NOTPIC.equals(respContent)) {
                    // 单图文消息不含图片 

                    Article article = new Article();
                    article.setTitle("微信公众帐号开发教程Java版");
                    // 图文消息中可以使用QQ表情、符号表情  
                    article
                        .setDescription("柳峰，"
                                        + QqFaceUtil.emoji(0x1F6B9)
                                        + "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");
                    // 将图片置为空  
                    article.setPicUrl("");
                    article.setUrl("http://blog.csdn.net/lyq8479");
                    articleList.add(article);

                    // 设置图文消息个数 
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合 
                    newsMessage.setArticles(articleList);

                    // 将图文消息对象转换成xml字符串 
                    respMessage = MessageUtil.newsMessageToXML(newsMessage);

                } else if (Constant.MULTI.equals(respContent)) {
                    // 多图文消息 

                    Article article1 = new Article();
                    article1.setTitle("微信公众帐号开发教程\n引言");
                    article1.setDescription("");
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");

                    Article article2 = new Article();
                    article2.setTitle("第2篇\n微信公众帐号的类型");
                    article2.setDescription("");
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");

                    Article article3 = new Article();
                    article3.setTitle("第3篇\n开发模式启用及接口配置");
                    article3.setDescription("");
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");

                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);

                    // 设置图文消息个数 
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合 
                    newsMessage.setArticles(articleList);

                    // 将图文消息对象转换成xml字符串 
                    respMessage = MessageUtil.newsMessageToXML(newsMessage);

                } else if (Constant.MULTI_FIRST_NOTPIC.equals(respContent)) {
                    // 多图文消息首条消息不含图片 

                    Article article1 = new Article();
                    article1.setTitle("微信公众帐号开发教程Java版");
                    article1.setDescription("");
                    // 将图片置为空  ]]]]]]]
                    article1.setPicUrl("");
                    article1.setUrl("http://blog.csdn.net/lyq8479");

                    Article article2 = new Article();
                    article2.setTitle("第4篇\n消息及消息处理工具的封装");
                    article2.setDescription("");
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");

                    Article article3 = new Article();
                    article3.setTitle("第5篇\n各种消息的接收与响应");
                    article3.setDescription("");
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");

                    Article article4 = new Article();
                    article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
                    article4.setDescription("");
                    article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
                    article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");

                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);
                    articleList.add(article4);

                    // 设置图文消息个数 
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合 
                    newsMessage.setArticles(articleList);

                    // 将图文消息对象转换成xml字符串 
                    respMessage = MessageUtil.newsMessageToXML(newsMessage);

                } else if (Constant.MULTI_LAST_NOTPIC.equals(respContent)) {
                    // 多图文消息最后一条消息不含图片

                    Article article1 = new Article();
                    article1.setTitle("第7篇\n文本消息中换行符的使用");
                    article1.setDescription("");
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");

                    Article article2 = new Article();
                    article2.setTitle("第8篇\n文本消息中使用网页超链接");
                    article2.setDescription("");
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");

                    Article article3 = new Article();
                    article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
                    article3.setDescription("");
                    // 将图片置为空  ]]]]]]
                    article3.setPicUrl("");
                    article3.setUrl("http://blog.csdn.net/lyq8479");

                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);

                    // 设置图文消息个数 
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合 
                    newsMessage.setArticles(articleList);

                    // 将图文消息对象转换成xml字符串 
                    respMessage = MessageUtil.newsMessageToXML(newsMessage);

                }

            } else if (MessageUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)) {
                respContent = "您发送的是图片消息！";
            } else if (MessageUtil.REQ_MESSAGE_TYPE_LOCATION.equals(msgType)) {
                respContent = "您发送的是地理位置消息！";
            } else if (MessageUtil.REQ_MESSAGE_TYPE_LINK.equals(msgType)) {
                respContent = "您发送的是链接消息！";
            } else if (MessageUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType)) {
                respContent = "您发送的是音频消息！";
            } else if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
                /**
                 * 如果请求消息类型为事件类型，需要判断是订阅、取消订阅、点击等等，进行相应处理
                 */

                // 回复文本消息  
                TextMessage textMessage = new TextMessage();
                // 回复给谁的消息
                textMessage.setToUserName(fromUserName);
                // 谁回复用户的请求消息
                textMessage.setFromUserName(toUserName);
                // 回复消息的时间
                textMessage.setCreateTime(new Date().getTime());
                // 回复消息的类型--响应之文本消息
                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                // 回复消息星标标识,零表示可以再提交
                textMessage.setFuncFlag(0);

                // 事件类型  
                String eventType = requestMap.get("Event");
                // 订阅 
                if (MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(eventType)) {
                    respContent = "谢谢您的关注！";
                } else if (MessageUtil.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息 
                } else if (MessageUtil.EVENT_TYPE_CLICK.equals(eventType)) {
                    // 自定义菜单点击事件 

                    // 获取事件KEY值，与创建自定义菜单时指定的KEY值对应 
                    String eventKey = requestMap.get("EventKey");
                    if (Constant.WEATHER_FORECAST_CLICKED.equals(eventKey)) {
                        respContent = "天气预报菜单项被点击！";

                    } else if (Constant.BUS_QUERY_CLICKED.equals(eventKey)) {
                        respContent = "公交查询菜单项被点击！";

                    } else if (Constant.PERIPHERY_SEARCH_CLICKED.equals(eventKey)) {
                        respContent = "周边搜索菜单项被点击！";

                    } else if (Constant.HISTORY_TODAY_CLICKED.equals(eventKey)) {
                        respContent = "历史上的今天菜单项被点击！";

                    } else if (Constant.SONG_PLAY_CLICKED.equals(eventKey)) {
                        respContent = "歌曲点播菜单项被点击！";

                    } else if (Constant.CLASSIC_GAME_CLICKED.equals(eventKey)) {
                        respContent = "经典游戏菜单项被点击！";

                    } else if (Constant.BEAUTY_RADIO_CLICKED.equals(eventKey)) {
                        respContent = "美女电台菜单项被点击！";

                    } else if (Constant.FACE_RECOGNITION_CLICKED.equals(eventKey)) {
                        respContent = "人脸识别菜单项被点击！";

                    } else if (Constant.CHAT_LAOKE_CLIKED.equals(eventKey)) {
                        respContent = "聊天唠嗑菜单项被点击！";

                    } else if (Constant.Q_CIRCLE_CLICKED.equals(eventKey)) {
                        respContent = "Q友圈菜单项被点击！";

                    } else if (Constant.FILM_RANK_CLICKED.equals(eventKey)) {
                        respContent = "电影排行榜菜单项被点击！";

                    } else if (Constant.HUMOROUS_JOKES_CLICKED.equals(eventKey)) {
                        respContent = "幽默笑话菜单项被点击！";

                    }
                }

                textMessage.setContent(respContent);
                // 将响应消息之文本消息对象转换为xml字符串
                respMessage = MessageUtil.textMessageToXML(textMessage);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }
}
