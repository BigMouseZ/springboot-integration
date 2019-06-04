package com.integration.create.simplefactory.client;

import com.integration.create.simplefactory.ChartFactory;
import com.integration.create.simplefactory.IChart;

/**
 * Created by ZhangGang on 2019/6/4.
 * 编写如下客户端测试代码：
 */
public class Client {
    public static void main(String args[]) {
        IChart chart1,chart2;
        chart1 = ChartFactory.getChart("pie");
        chart2 = ChartFactory.getChart("histogram"); //通过静态工厂方法创建产品
        chart1.display();
        chart2.display();

        /*
        * Chart chart;
		String type = XMLUtil.getChartType(); //读取配置文件中的参数
		chart = ChartFactory.getChart(type); //创建产品对象
		chart.display();
*/
    }

}
