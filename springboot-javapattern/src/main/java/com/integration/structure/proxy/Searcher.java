package com.integration.structure.proxy;

/**
 * Created by ZhangGang on 2019/6/5.
 */
//抽象查询类，充当抽象主题角色，它声明了DoSearch()方法。
public interface Searcher {
    String DoSearch(String userId, String keyword);
}
