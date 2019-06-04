package com.integration.create.simplefactory.impl;


import com.integration.create.simplefactory.IChart;

/**
 * Created by ZhangGang on 2019/6/4.
 */

//饼状图类：具体产品类
public class PieChart implements IChart{
    public PieChart() {
        System.out.println("创建饼状图！");
    }
    @Override
    public void display() {
        System.out.println("显示饼状图！");
    }

}
