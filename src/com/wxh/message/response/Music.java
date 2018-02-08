/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.response;

/**
 * 响应消息之音乐消息中Music类的定义
 *   <Music>
 *       <Title>< ![CDATA[TITLE] ]></Title>
 *       <Description>< ![CDATA[DESCRIPTION] ]></Description>
 *       <MusicUrl>< ![CDATA[MUSIC_Url] ]></MusicUrl>
 *       <HQMusicUrl>< ![CDATA[HQ_MUSIC_Url] ]></HQMusicUrl>
 *   </Music>
 *   
 * @author wxh
 * @version $Id: Music.java, v 0.1 2018年2月7日 下午1:53:51 wxh Exp $
 */
public class Music {

    /**
     * 音乐标题  
     */
    private String Title;

    /**
     * 音乐描述 
     */
    private String Description;

    /**
     * 音乐链接 
     */
    private String MusicUrl;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;

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

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String hQMusicUrl) {
        HQMusicUrl = hQMusicUrl;
    }

}
