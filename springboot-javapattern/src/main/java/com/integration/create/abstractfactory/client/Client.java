package com.integration.create.abstractfactory.client;

import com.integration.create.abstractfactory.Button;
import com.integration.create.abstractfactory.ComboBox;
import com.integration.create.abstractfactory.SkinFactory;
import com.integration.create.abstractfactory.TextField;
import com.integration.create.abstractfactory.impl.SpringSkinFactory;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args) {
        //使用抽象层定义
        SkinFactory factory;
        Button bt;
        TextField tf;
        ComboBox cb;
        factory = new SpringSkinFactory();
        bt = factory.createButton();
        tf = factory.createTextField();
        cb = factory.createComboBox();
        bt.display();
        tf.display();
        cb.display();

    }
}
