/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author wxh
 * @version $Id: DateUtil.java, v 0.1 2018年2月8日 上午10:38:39 wxh Exp $
 */
public class DateUtil {

    private DateUtil() {

    }

    /**
     * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
     * @param createTime 消息创建时间 
     * @return String
     */
    public static String formatDate(String createTime) {
        // 微信消息接口中的CreateTime表示距离1970年的秒数，而System.currentTimeMillis()表示距离1970年的毫秒数
        // 将微信传入的CreateTime转换成long类型，再乘以1000 
        long msgCreateTime = Long.parseLong(createTime) * 1000;
        // 指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化
        return sdf.format(new Date(msgCreateTime));
    }
}
