/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.response;

/**
 * 响应消息之音乐消息
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <Music>
 *       <Title>< ![CDATA[TITLE] ]></Title>
 *       <Description>< ![CDATA[DESCRIPTION] ]></Description>
 *       <MusicUrl>< ![CDATA[MUSIC_Url] ]></MusicUrl>
 *       <HQMusicUrl>< ![CDATA[HQ_MUSIC_Url] ]></HQMusicUrl>
 *       <ThumbMediaId>< ![CDATA[media_id] ]></ThumbMediaId>
 *   </Music>
 * </xml>
 * 
 * @author wxh
 * @version $Id: MusicMessage.java, v 0.1 2018年2月7日 下午1:49:09 wxh Exp $
 */
public class MusicMessage extends BaseMessage {

    /**
     * 把Music标签封装成一个类
     */
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

}
