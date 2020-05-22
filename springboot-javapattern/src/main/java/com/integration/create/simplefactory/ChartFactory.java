package com.integration.create.simplefactory;

import com.integration.create.simplefactory.impl.HistogramChart;
import com.integration.create.simplefactory.impl.LineChart;
import com.integration.create.simplefactory.impl.PieChart;

/**
 * Created by ZhangGang on 2019/6/4.
 */
//Factory（工厂角色)
public class ChartFactory {
    //静态工厂方法
    public static IChart getChart(String type) {
        IChart chart = null;
        if (type.equalsIgnoreCase("histogram")) {
            chart = new HistogramChart();
            System.out.println("初始化设置柱状图！");
        } else if (type.equalsIgnoreCase("pie")) {
            chart = new PieChart();
            System.out.println("初始化设置饼状图！");
        } else if (type.equalsIgnoreCase("line")) {
            chart = new LineChart();
            System.out.println("初始化设置折线图！");
        }
        return chart;
    }


}
