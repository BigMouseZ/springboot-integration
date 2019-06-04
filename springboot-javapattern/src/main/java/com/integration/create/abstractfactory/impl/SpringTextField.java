package com.integration.create.abstractfactory.impl;

import com.integration.create.abstractfactory.TextField;

/**
 * Created by ZhangGang on 2019/6/4.
 */
//
//Spring文本框类：具体产品
public class SpringTextField  implements TextField{
    public void display() {
        System.out.println("显示绿色边框文本框。");
    }
}
