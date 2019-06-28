package com.integration.action.strategy;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
 //折扣类：抽象策略类
public interface Discount {
    public double calculate(double price);
}
