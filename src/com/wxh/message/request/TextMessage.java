/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.request;

/**
 * 请求消息之文本消息
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <Content>< ![CDATA[this is a test] ]></Content> 
 *   <MsgId>1234567890123456</MsgId>
 * </xml>
 * 
 * @author wxh
 * @version $Id: TextMessage.java, v 0.1 2018年2月7日 上午11:29:40 wxh Exp $
 */
public class TextMessage extends BaseMessage {

    /**
     * 消息内容 
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
