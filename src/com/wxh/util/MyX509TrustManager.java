/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 证书信任管理器---用户HTTPS请求处理，主要针对微信创建菜单功能实现(基本HTTPS的post请求)
 * @author wxh
 * @version $Id: MyX509TrustManager.java, v 0.1 2018年2月8日 下午2:22:54 wxh Exp $
 */
public class MyX509TrustManager implements X509TrustManager {

    /** 
     * 检查客户端的信任证书
     * @param chain
     * @param authType
     * @throws CertificateException
     */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
                                                                            throws CertificateException {
    }

    /** 
     * 检查服务器的信任证书
     * @param chain
     * @param authType
     * @throws CertificateException
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
                                                                            throws CertificateException {
    }

    /** 
     * 获取接收发行的证书
     * @return X509Certificate数组
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}
