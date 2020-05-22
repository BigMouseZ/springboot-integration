package com.integration.structure.proxy.impl;

import com.integration.structure.proxy.Searcher;

/**
 * Created by ZhangGang on 2019/6/5.
 */
//具体查询类，充当真实主题角色，它实现查询功能，提供方法DoSearch()来查询信息
public class RealSearcher implements Searcher {

    @Override
    public String DoSearch(String userId, String keyword) {
        System.out.println("用户"+userId+"使用关键词"+keyword+"'查询商务信息！");
        return "返回具体内容";

    }
}
