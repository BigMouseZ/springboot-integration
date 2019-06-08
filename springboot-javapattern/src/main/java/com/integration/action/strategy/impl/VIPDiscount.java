package com.integration.action.strategy.impl;

import com.integration.action.strategy.Discount;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
//VIP会员票折扣类：具体策略类
public class VIPDiscount implements Discount {
    public double calculate(double price) {
        System.out.println("VIP票：");
        System.out.println("增加积分！");
        return price * 0.5;
    }
}
