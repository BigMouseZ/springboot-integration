package com.integration.action.chainofresponsibility.impl;

import com.integration.action.chainofresponsibility.Approver;
import com.integration.action.chainofresponsibility.PurchaseRequest;

/**
 * Created by ZhangGang on 2019/6/5.
 */
public class Congress extends Approver {
    public Congress(String name) {
        super(name);
    }

    //具体请求处理方法
    public void processRequest(PurchaseRequest request) {
        System.out.println("召开董事会审批采购单：" + request.getNumber() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");        //处理请求
    }
}
