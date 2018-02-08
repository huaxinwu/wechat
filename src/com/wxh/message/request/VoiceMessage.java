/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.request;

/**
 * 请求消息之语音消息
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <MediaId>< ![CDATA[media_id] ]></MediaId>
 *   <Format>< ![CDATA[Format] ]></Format>
 *   <MsgId>1234567890123456</MsgId> 
 * </xml>
 * 
 * @author wxh
 * @version $Id: VoiceMessage.java, v 0.1 2018年2月7日 上午11:50:01 wxh Exp $
 */
public class VoiceMessage extends BaseMessage {

    /**
     * 媒体ID 
     */
    private String MediaId;

    /**
     * 语音格式 
     */
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

}
