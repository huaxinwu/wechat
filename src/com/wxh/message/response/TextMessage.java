/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.response;

/**
 * 响应消息之文本消息
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <Content>< ![CDATA[你好] ]></Content>
 * </xml>
 * 
 * @author wxh
 * @version $Id: TextMessage.java, v 0.1 2018年2月7日 下午1:43:41 wxh Exp $
 */
public class TextMessage extends BaseMessage {

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
