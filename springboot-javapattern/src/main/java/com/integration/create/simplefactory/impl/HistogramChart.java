package com.integration.create.simplefactory.impl;

import com.integration.create.simplefactory.IChart;

/**
 * Created by ZhangGang on 2019/6/4.
 */
// ConcreteProduct（具体产品角色）
public class HistogramChart implements IChart {
    public HistogramChart() {
        System.out.println("创建柱状图！");
    }

    @Override
    public void display() {
        System.out.println("显示柱状图！");
    }

}
