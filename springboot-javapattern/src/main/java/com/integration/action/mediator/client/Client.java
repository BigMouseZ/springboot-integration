package com.integration.action.mediator.client;

import com.integration.action.mediator.impl.Button;
import com.integration.action.mediator.impl.ComboBox;
import com.integration.action.mediator.impl.ConcreteMediator;
import com.integration.action.mediator.impl.List;
import com.integration.action.mediator.impl.TextBox;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args) {
        //定义中介者对象
        ConcreteMediator mediator;
        mediator = new ConcreteMediator();

        //定义同事对象
        Button addBT = new Button();
        List list = new List();
        ComboBox cb = new ComboBox();
        TextBox userNameTB = new TextBox();

        addBT.setMediator(mediator);
        list.setMediator(mediator);
        cb.setMediator(mediator);
        userNameTB.setMediator(mediator);

        mediator.addButton = addBT;
        mediator.list = list;
        mediator.cb = cb;
        mediator.userNameTextBox = userNameTB;

        addBT.changed();
        System.out.println("-----------------------------");
        list.changed();
    }
}
