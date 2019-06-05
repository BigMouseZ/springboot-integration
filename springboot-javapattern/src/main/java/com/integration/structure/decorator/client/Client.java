package com.integration.structure.decorator.client;

import com.integration.structure.decorator.Component;
import com.integration.structure.decorator.impl.BlackBorderDecorator;
import com.integration.structure.decorator.impl.ScrollBarDecorator;
import com.integration.structure.decorator.impl.Window;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {

    public static void main(String args[]) {
        onceDecorator();
        System.out.println("===============");
        secondDecorator();

    }
    private static void onceDecorator() {

        Component component, componentSB; //使用抽象构件定义
        component = new Window(); //定义具体构件
        componentSB = new ScrollBarDecorator(component); //定义装饰后的构件
        componentSB.display();
    }

    private static void secondDecorator() {

        Component component, componentSB, componentBB; //全部使用抽象构件定义
        component = new Window();
        componentSB = new ScrollBarDecorator(component);
        componentBB = new BlackBorderDecorator(componentSB); //将装饰了一次之后的对象继续注入到另一个装饰类中，进行第二次装饰
        componentBB.display();
    }

}

