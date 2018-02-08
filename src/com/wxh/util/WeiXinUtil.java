/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxh.pojo.AccessToken;
import com.wxh.pojo.Menu;

/**
 * 微信公众平台通用接口工具类 
 * 处理各种HTTPS请求
 * @author wxh
 * @version $Id: WeiXinUtil.java, v 0.1 2018年2月8日 下午2:27:52 wxh Exp $
 */
public class WeiXinUtil {

    /** 记录该类的日志   */
    private static final Logger logger           = LoggerFactory.getLogger(WeiXinUtil.class);

    /**
     * 获取access_token的接口地址（GET） 限200（次/天） 
     */
    public final static String  ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 菜单创建（POST） 限100（次/天） 
     */
    public static String        MENU_CREATE_URL  = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private WeiXinUtil() {

    }

    /**
     * 创建菜单
     * @param menu 菜单对象
     * @param accessToken 有效的access_token 
     * @return int 
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        // 拼装创建菜单的URL
        String requestUrl = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串  
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用API接口创建菜单 
        JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonMenu);
        // 如果请求成功
        if (null != jsonObject) {
            // {"errcode":0,"errmsg":"ok"}，表示 成功
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
                    jsonObject.getString("errmsg"));

            }
        }
        return result;
    }

    /**
     * 获取微信的凭证对象--GET请求
     * @param appId 凭证ID
     * @param appSecret 凭证密钥
     * @return AccessToken
     */
    public static AccessToken getAccessToken(String appId, String appSecret) {
        AccessToken accessToken = null;
        // 在代码中替换URL中APPID、APPSECRET
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appId)
            .replace("APPSECRET", appSecret);
        // 处理用户发起请求，结果为json对象
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                // 微信服务器返回凭证json格式获取参数
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (Exception e) {
                accessToken = null;
                // 获取token失败  ,设置微信服务器获取凭证失败json格式
                // {"errcode":40018,"errmsg":"invalid button name size"}，表示失败
                logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
                    jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    /**
     * 用户发起https请求(URL)并获取结果 
     * @param requestUrl 请求URL
     * @param requestMethod 请求方法
     * @param outputStr 输出字符串
     * @return JSONObject json对象
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        // 字符串缓冲区
        StringBuffer buffer = new StringBuffer();

        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化 
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 根据SSLContext对象中得到SSLSocketFactory对象 
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            // 设置HTTPS的URL
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            // 设置输入、输出
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);

            // 如果请求是GET,直接连接
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时  --POST请求
            if (null != outputStr) {
                // 获取字节输出流
                OutputStream out = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码  
                out.write(outputStr.getBytes("UTF-8"));
                out.close();
            }

            //获取字节输入流并转换成字符串  
            InputStream in = httpUrlConn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 关闭流
            bufferedReader.close();
            reader.close();
            in.close();
            in = null;

            // 断开连接
            httpUrlConn.disconnect();

            // 字符串转换json对象
            jsonObject = JSONObject.fromObject(buffer.toString());

        } catch (NoSuchAlgorithmException e) {
            logger.error("没有这样的算法异常！", e);
        } catch (NoSuchProviderException e) {
            logger.error("没有这样的提供程序的异常！", e);
        } catch (KeyManagementException e) {
            logger.error("密钥管理异常！", e);
        } catch (MalformedURLException e) {
            logger.error("畸形URL异常！", e);
        } catch (IOException e) {
            logger.error("IO异常!", e);
        }

        return jsonObject;
    }

}
