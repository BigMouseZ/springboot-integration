package com.integration.action.mediator.impl;

import com.integration.action.mediator.Component;

/**
 * Created by ZhangGang on 2019/6/5.
 */
 //组合框类：具体同事类
public class ComboBox extends Component {
    public void update() {
        System.out.println("组合框增加一项：张无忌。");
    }

    public void select() {
        System.out.println("组合框选中项：小龙女。");
    }
}
