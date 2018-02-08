/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.request;

/**
 * 请求消息之图片消息
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <PicUrl>< ![CDATA[this is a url] ]></PicUrl>
 *   <MsgId>1234567890123456</MsgId> 
 * </xml>
 * 
 * @author wxh
 * @version $Id: ImageMessage.java, v 0.1 2018年2月7日 上午11:32:47 wxh Exp $
 */
public class ImageMessage extends BaseMessage {

    /**
     * 图片链接 
     */
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

}
