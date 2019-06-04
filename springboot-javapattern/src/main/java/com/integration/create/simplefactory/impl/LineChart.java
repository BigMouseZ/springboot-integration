package com.integration.create.simplefactory.impl;

import com.integration.create.simplefactory.IChart;

/**
 * Created by ZhangGang on 2019/6/4.
 */

//折线图类：具体产品类
public class LineChart implements IChart {
    public LineChart() {
        System.out.println("创建折线图！");
    }

    @Override
    public void display() {
        System.out.println("显示折线图！");
    }

}
