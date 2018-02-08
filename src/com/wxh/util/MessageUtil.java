/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wxh.message.response.Article;
import com.wxh.message.response.MusicMessage;
import com.wxh.message.response.NewsMessage;
import com.wxh.message.response.TextMessage;

/**
 * 消息(请求消息和响应消息)工具类
 * 1.请求消息：解析微信服务器发送过来的消息---解析xml格式消息(dom4j-1.6.1.jar)，存入map中，
 * 2.响应消息：响应给微信服务器需要xml格式，这里需要转换(xstream-1.3.1.jar)
 * @author wxh
 * @version $Id: MessageUtil.java, v 0.1 2018年2月7日 下午2:19:52 wxh Exp $
 */
public class MessageUtil {

    /**
     * 扩展xstream，使其支持CDATA块 
     */
    private static XStream     xstream                   = new XStream(new XppDriver() {
                                                             public HierarchicalStreamWriter createWriter(Writer out) {
                                                                 return new PrettyPrintWriter(out) {
                                                                     // 对所有xml节点的转换都增加CDATA标记  
                                                                     boolean cdata = true;

                                                                     @SuppressWarnings("unchecked")
                                                                     public void startNode(String name,
                                                                                           Class clazz) {
                                                                         super.startNode(name,
                                                                             clazz);
                                                                     }

                                                                     /**
                                                                      * 拼接CDATA标记 和消息内容text
                                                                      * @param writer
                                                                      * @param text
                                                                      */
                                                                     protected void writeText(QuickWriter writer,
                                                                                              String text) {
                                                                         if (cdata) {
                                                                             writer
                                                                                 .write("<![CDATA[");
                                                                             writer.write(text);
                                                                             writer.write("]]>");
                                                                         } else {
                                                                             writer.write(text);
                                                                         }
                                                                     }
                                                                 };
                                                             }
                                                         });

    /** 
     * 返回消息类型：文本 
     */
    public static final String RESP_MESSAGE_TYPE_TEXT    = "text";

    /** 
     * 返回消息类型：音乐 
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC   = "music";

    /** 
     * 返回消息类型：图文 
     */
    public static final String RESP_MESSAGE_TYPE_NEWS    = "news";

    //----------------------------------------------------------------------
    // ------- 华丽分割线---------------------------------------------
    //----------------------------------------------------------------------

    /** 
     * 请求消息类型：文本 
     */
    public static final String REQ_MESSAGE_TYPE_TEXT     = "text";

    /** 
     * 请求消息类型：图片 
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE    = "image";

    /** 
     * 请求消息类型：链接 
     */
    public static final String REQ_MESSAGE_TYPE_LINK     = "link";

    /** 
     * 请求消息类型：地理位置 
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /** 
     * 请求消息类型：音频 
     */
    public static final String REQ_MESSAGE_TYPE_VOICE    = "voice";

    /** 
     * 请求消息类型：推送 
     */
    public static final String REQ_MESSAGE_TYPE_EVENT    = "event";

    /** 
     * 事件类型：subscribe(订阅) 
     */
    public static final String EVENT_TYPE_SUBSCRIBE      = "subscribe";

    /** 
     * 事件类型：unsubscribe(取消订阅) 
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE    = "unsubscribe";

    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */
    public static final String EVENT_TYPE_CLICK          = "CLICK";

    private MessageUtil() {

    }

    /**
     * 解析微信服务器发送的请求消息(xml格式的)
     * @param request
     * @return Map
     * @throws Exception
     */
    public static Map<String, String> parseXML(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中 
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流 
        InputStream in = request.getInputStream();

        //======================dom4j解析xml的过程==============================================

        // 读取输入流--dom4j中的类
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        // 获取xml根元素
        Element root = document.getRootElement();
        // 获取根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点 
        for (Element e : elementList) {
            // 按照节点名称作为key，节点文本内容作为value
            map.put(e.getName(), e.getText());
        }

        // 关闭流
        in.close();
        // JVM GC回收垃圾
        in = null;

        return map;
    }

    /**
     * 响应消息之文本消息对象转换成xml
     * @param textMessage 文本消息对象(响应)
     * @return String
     */
    public static String textMessageToXML(TextMessage textMessage) {
        // xstream将Java对象转换为xml格式字符串
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 响应消息之音乐消息对象转换成xml
     * @param musicMessage  音乐消息对象 
     * @return String
     */
    public static String musicMessageToXML(MusicMessage musicMessage) {
        // xstream将Java对象转换为xml格式字符串
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /** 
     * 图文消息对象转换成xml 
     * @param newsMessage 图文消息对象 
     * @return xml 
     */
    public static String newsMessageToXML(NewsMessage newsMessage) {
        // xstream将Java对象转换为xml格式字符串
        xstream.alias("xml", newsMessage.getClass());
        // 图文消息多了item标签
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 计算采用UTF-8编码方式时字符串所占字节数 
     * @param content
     * @return int
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            // 汉字采用utf-8编码时占3个字节 
            try {
                size = content.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                System.out.println("不支持编码异常!");
            }

        }
        return size;
    }

    /**
     * 在文本消息中使用换行符
     * 获取微信界面主菜单
     * @return String
     */
    public static String getMainMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("您好，我是小星，请回复数字选择服务：").append("\n\n");
        buffer.append("1  天气预报").append("\n");
        buffer.append("2  公交查询").append("\n");
        buffer.append("3  周边搜索").append("\n");
        buffer.append("4  歌曲点播").append("\n");
        buffer.append("5  经典游戏").append("\n");
        buffer.append("6  美女电台").append("\n");
        buffer.append("7  人脸识别").append("\n");
        buffer.append("8  聊天唠嗑").append("\n\n");
        buffer.append("回复“?”显示此帮助菜单");
        return buffer.toString();
    }

    // ===============还可以扩展其他消息类型===============================

}
