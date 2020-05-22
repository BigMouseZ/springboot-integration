package com.integration.action.templatemethod.impl;

import com.integration.action.templatemethod.Account;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
//活期账户类，充当具体子类。
public class CurrentAccount extends Account {

    @Override
    public void CalculateInterest() {
        System.out.println("按活期利率计算利息！");
    }
}
