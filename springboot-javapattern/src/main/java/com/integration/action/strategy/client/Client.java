package com.integration.action.strategy.client;

import com.integration.action.strategy.Discount;
import com.integration.action.strategy.MovieTicket;
import com.integration.action.strategy.impl.ChildrenDiscount;
import com.integration.action.strategy.impl.StudentDiscount;
import com.integration.action.strategy.impl.VIPDiscount;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args) {
        MovieTicket mt = new MovieTicket();
        double originalPrice = 60.0;
        double currentPrice;

        mt.setPrice(originalPrice);
        System.out.println("原始价为：" + originalPrice);
        System.out.println("---------------------------------");

        Discount childrenDiscount = new ChildrenDiscount();
        Discount studentDiscount = new StudentDiscount();
        Discount vipDiscount = new VIPDiscount();
        //  discount = (Discount)XMLUtil.getBean(); //读取配置文件并反射生成具体折扣对象
        mt.setDiscount(childrenDiscount); //注入折扣对象
        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
        mt.setDiscount(studentDiscount); //注入折扣对象
        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
        mt.setDiscount(vipDiscount); //注入折扣对象
        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
    }
}
