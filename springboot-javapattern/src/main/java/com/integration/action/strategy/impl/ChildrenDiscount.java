package com.integration.action.strategy.impl;

import com.integration.action.strategy.Discount;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
 //儿童票折扣类：具体策略类
public class ChildrenDiscount implements Discount {
    public double calculate(double price) {
        System.out.println("儿童票：");
        return price - 10;
    }
}