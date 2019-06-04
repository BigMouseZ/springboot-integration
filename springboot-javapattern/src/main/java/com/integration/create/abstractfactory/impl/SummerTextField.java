package com.integration.create.abstractfactory.impl;

import com.integration.create.abstractfactory.TextField;

/**
 * Created by ZhangGang on 2019/6/4.
 */

//Summer文本框类：具体产品
public class SummerTextField implements TextField {
    public void display() {
        System.out.println("显示蓝色边框文本框。");
    }
}
