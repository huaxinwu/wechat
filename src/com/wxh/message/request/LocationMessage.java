/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.request;

/**
 * 请求消息之地理位置消息
 * <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *   <Location_X>23.134521</Location_X>
 *   <Location_Y>113.358803</Location_Y>
 *   <Scale>20</Scale>
 *   <Label>< ![CDATA[位置信息] ]></Label>
 *   <MsgId>1234567890123456</MsgId> 
 * </xml>
 * 
 * @author wxh
 * @version $Id: LocationMessage.java, v 0.1 2018年2月7日 上午11:39:28 wxh Exp $
 */
public class LocationMessage extends BaseMessage {

    /**
     * 地理位置维度
     */
    private String Location_X;

    /**
     * 地理位置经度 
     */
    private String Location_Y;

    /**
     * 地图缩放大小 
     */
    private String Scale;

    /**
     * 地理位置信息
     */
    private String Label;

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

}
