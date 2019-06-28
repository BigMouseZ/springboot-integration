package com.integration.action.command.client;

import com.integration.action.command.impl.TestEntity;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args) {
        TestEntity aa = new TestEntity();
        System.out.println(aa.getTest());
    }
}
