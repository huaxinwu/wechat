/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxh.service.CoreService;
import com.wxh.util.SignUtil;

/**
 * 处理微信服务器转发用户发来的请求处理类
 * @author wxh
 * @version $Id: CoreServlet.java, v 0.1 2018年2月7日 上午10:19:07 wxh Exp $
 */
public class CoreServlet extends HttpServlet {

    /** */
    private static final long serialVersionUID = -1270427129898874182L;

    public CoreServlet() {
        super();
    }

    public void init() throws ServletException {
        // Put your code here
    }

    public void destroy() {
        super.destroy();
    }

    /**
     * 确认发送过来的请求来自微信服务器转发的 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                                                               throws ServletException,
                                                                               IOException {
        /**
         * 自己配置的微信公众平台的服务器配置，微信服务器(腾讯)用四个字段加密解析对比一样，才会让的请求验证通过
         */

        // 微信加密签名 
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数  
        String nonce = request.getParameter("nonce");
        // 随机字符串 
        String echostr = request.getParameter("echostr ");

        // 字节打印输出流
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败 
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.write(echostr);
        }
        // 关闭流
        out.close();
        // 弱引用 JVM GC回收
        out = null;
    }

    /**
     * 处理微信服务器发来的消息 (图片、文本、音频、视频等等)
     * 用户--->微信公众号-->微信服务器-->自己开发程序(微信配置服务器Url)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                                                                throws ServletException,
                                                                                IOException {
        // 消息的接收、处理、响应  
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息  
        String responseMessage = CoreService.processRequest(request);

        // 响应用户的消息
        PrintWriter out = response.getWriter();
        out.print(responseMessage);

        // 关闭流
        out.close();
    }

}
