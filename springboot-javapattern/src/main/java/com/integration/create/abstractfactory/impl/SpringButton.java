package com.integration.create.abstractfactory.impl;

import com.integration.create.abstractfactory.Button;

/**
 * Created by ZhangGang on 2019/6/4.
 */
//
//Spring按钮类：具体产品
public class SpringButton implements Button {
    public void display() {
        System.out.println("显示浅绿色按钮。");
    }

}
