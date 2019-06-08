package com.integration.action.templatemethod.impl;

import com.integration.action.templatemethod.Account;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
public class SavingAccount extends Account {
    @Override
    public void CalculateInterest() {
        System.out.println("按定期利率计算利息！");
    }
}
