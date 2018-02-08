/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求校验工具类
 * @author wxh
 * @version $Id: SignUtil.java, v 0.1 2018年2月7日 上午10:30:04 wxh Exp $
 */
public class SignUtil {

    /** 与微信公众平台服务器配置中Token保持一致 ,否则无法验证通过  */
    private static final String TOKEN = "wechat";

    private SignUtil() {

    }

    /**
     * 签名验证
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return boolean
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        // 初始化一个字符串数组
        String[] array = new String[] { TOKEN, timestamp, nonce };
        // 将TOKEN、timestamp、nonce三个参数进行字典序排序  
        Arrays.sort(array);
        // 字符串拼接最好用StringBuilder，因为效率高，要求线程安全时候，再用StringBuffer
        StringBuilder sb = new StringBuilder();
        // 减少CPU计算时间，提高性能
        int len = array.length;
        // 遍历数组，将各个元素添加到StringBuilder
        for (int i = 0; i < len; i++) {
            sb.append(array[i]);
        }

        // 使用SHA-256加密，因为SHA-1已经被谷歌破解
        MessageDigest messageDigest = null;
        String tempStr = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            // 将三个参数字符串拼接成一个字符串进行SHA256加密  ,注意加密一般都是处理字节数组
            byte[] arr = messageDigest.digest(sb.toString().getBytes());
            tempStr = byteToString(arr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("没有这样的算法异常！");
        }
        sb = null;
        // 将SHA256加密后的字符串可与微信服务器signature对比，标识该请求来源于微信 服务器转发的
        return tempStr != null ? tempStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为字符串
     * @param arr
     * @return String
     */
    public static String byteToString(byte[] arr) {
        String str = "";
        int len = arr.length;
        // 遍历，将字节数组的元素转换为十六进制的字符串
        for (int i = 0; i < len; i++) {
            str += byteToHexString(arr[i]);
        }
        return str;
    }

    /**
     * 将字节转换为十六进制的字符串
     * @param byteStr
     * @return String
     */
    public static String byteToHexString(byte byteStr) {
        // 十六进制各个字符
        char[] digest = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F' };
        // 一个字符等于两个字节
        char[] tempArray = new char[2];
        // 赋值，第一个元素无符号右移4位，再位与运算
        tempArray[0] = digest[(byteStr >>> 4) & 0X0F];
        // 第二个元素位与运算
        tempArray[1] = digest[byteStr & 0X0F];
        // 将字符数组转换为字符串
        String str = new String(tempArray);
        return str;
    }
}
