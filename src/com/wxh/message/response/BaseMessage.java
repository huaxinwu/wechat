/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.response;

/**
 * 响应消息基类（公众帐号 -> 普通用户）
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <FuncFlag>0</FuncFlag>
 * </xml>
 * @author wxh
 * @version $Id: BaseMessage.java, v 0.1 2018年2月7日 上午11:53:40 wxh Exp $
 */
public class BaseMessage {

    /**
     * 接收方帐号（收到的OpenID）
     */
    private String ToUserName;

    /**
     * 开发者微信号 ----发送消息方
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
     * 消息的星标标识
     * 位0x0001被标志时，星标刚收到的消息  
     */
    private int    FuncFlag;

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

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }

}
