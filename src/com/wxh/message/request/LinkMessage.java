/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.request;

/**
 * 请求消息之链接消息
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <Title>< ![CDATA[公众平台官网链接] ]></Title>
 *   <Description>< ![CDATA[公众平台官网链接] ]></Description>
 *   <Url>< ![CDATA[url] ]></Url>
 *   <MsgId>1234567890123456</MsgId> 
 * </xml>
 * 
 * @author wxh
 * @version $Id: LinkMessage.java, v 0.1 2018年2月7日 上午11:45:14 wxh Exp $
 */
public class LinkMessage extends BaseMessage {

    /**
     * 消息标题
     */
    private String Title;

    /**
     * 消息描述
     */
    private String Description;

    /**
     * 消息链接 
     */
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
