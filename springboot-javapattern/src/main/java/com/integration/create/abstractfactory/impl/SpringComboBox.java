package com.integration.create.abstractfactory.impl;

import com.integration.create.abstractfactory.ComboBox;

/**
 * Created by ZhangGang on 2019/6/4.
 */

//Spring组合框类：具体产品
public class SpringComboBox implements ComboBox {
    public void display() {
        System.out.println("显示绿色边框组合框。");
    }

}
