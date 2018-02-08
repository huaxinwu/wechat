/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微信中发送QQ表情---文本消息
 * @author wxh
 * @version $Id: QqFaceUtil.java, v 0.1 2018年2月8日 上午10:17:17 wxh Exp $
 */
public class QqFaceUtil {

    private QqFaceUtil() {

    }

    /**
     * 通过正则表达式实现的，判断是否是QQ表情
     * @param content
     * @return boolean
     */
    public static boolean isQqFace(String content) {
        boolean result = false;
        // 判断QQ表情的正则表达式  
        String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";

        // 正则表达式类
        Pattern pattern = Pattern.compile(qqfaceRegex);
        Matcher matcher = pattern.matcher(content);
        // 如果匹配，赋值为true
        if (matcher.matches()) {
            result = true;
        }
        return result;
    }

    /**
     * emoji表情转换(hex -> utf-16) 
     * 直接在文本消息的Content里写emoji表情代码，一定是会原样显示的
     * @param hexEmoji
     * @return String
     */
    public static String emoji(int hexEmoji) {
        // 自动装箱
        return String.valueOf(Character.toChars(hexEmoji));
    }

}
