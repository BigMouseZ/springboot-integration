package com.integration.structure.proxy.client;

import com.integration.structure.proxy.Searcher;
import com.integration.structure.proxy.impl.ProxySearcher;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args) {
        //读取配置文件
     //   String proxy = ConfigurationManager.AppSettings["proxy"];
        //反射生成对象，针对抽象编程，客户端无须分辨真实主题类和代理类
        Searcher searcher = new ProxySearcher();
                //(Searcher)Assembly.Load("ProxySample").CreateInstance(proxy);
        String result = searcher.DoSearch("杨过", "玉女心经");
        System.out.println("执行完毕:"+result);
    }
}
