/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.request;

/**
 * 请求消息基类（普通用户 -> 公众帐号）
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <MsgId>1234567890123456</MsgId>
 * </xml>
 * 
 * @author wxh
 * @version $Id: BaseMessage.java, v 0.1 2018年2月7日 上午11:14:55 wxh Exp $
 */
public class BaseMessage {

    /**
     * 开发者微信号(接收者),这里注意变量名必须与微信公众平台文件xml格式标签字段名一致，否则无法解析
     */
    private String ToUserName;

    /**
     * 发送方帐号（一个OpenID）--发送者
     */
    private String FromUserName;

    /**
     * 消息创建时间 （整型）
     */
    private long   CreateTime;

    /**
     * 消息类型（文本消息、图片消息、地理位置消息、链接消息）
     */
    private String MsgType;

    /**
     * 消息id，64位整型  
     */
    private long   MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

}
