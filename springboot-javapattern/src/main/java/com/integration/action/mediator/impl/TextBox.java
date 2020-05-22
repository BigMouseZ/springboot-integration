package com.integration.action.mediator.impl;

import com.integration.action.mediator.Component;

/**
 * Created by ZhangGang on 2019/6/5.
 */
public //文本框类：具体同事类
class TextBox extends Component {
    public void update() {
        System.out.println("客户信息增加成功后文本框清空。");
    }

    public void setText() {
        System.out.println("文本框显示：小龙女。");
    }
}