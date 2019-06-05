package com.integration.structure.decorator.impl;

import com.integration.structure.decorator.Component;
import com.integration.structure.decorator.ComponentDecorator;

/**
 * Created by ZhangGang on 2019/6/5.
 */
//黑色边框装饰类：具体装饰类
public class BlackBorderDecorator extends ComponentDecorator {
    public BlackBorderDecorator(Component component) {
        super(component);
    }

    public void display() {
        this.setBlackBorder();
        super.display();

    }

    public void setBlackBorder() {
        System.out.println("为构件增加黑色边框！");
    }
}
