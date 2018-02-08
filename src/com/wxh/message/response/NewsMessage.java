/**
 * wxh Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.wxh.message.response;

import java.util.List;

/**
 * 响应消息之图文消息
* <xml>
 *   <ToUserName>< ![CDATA[ToUser] ]></ToUserName>
 *   <FromUserName>< ![CDATA[FromUser] ]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType>< ![CDATA[event] ]></MsgType>
 *  <ArticleCount>2</ArticleCount>
 *   <Articles>
 *       <item>
 *           <Title>< ![CDATA[title1] ]></Title>
 *           <Description>< ![CDATA[description1] ]></Description>
 *           <PicUrl>< ![CDATA[picurl] ]></PicUrl>
 *           <Url>< ![CDATA[url] ]></Url>
 *       </item>
 *       <item>
 *           <Title>< ![CDATA[title] ]></Title>
 *           <Description>< ![CDATA[description] ]></Description>
 *           <PicUrl>< ![CDATA[picurl] ]></PicUrl>
 *           <Url>< ![CDATA[url] ]> </Url>
 *       </item>
 *   </Articles>
* </xml>
* 
 * @author wxh
 * @version $Id: NewsMessage.java, v 0.1 2018年2月7日 下午1:59:18 wxh Exp $
 */
public class NewsMessage extends BaseMessage {

    /**
     * 图文消息个数，限制为10条以内
     */
    private int   ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图 
     */
    List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }

}
