package com.integration.action.chainofresponsibility;

import lombok.Data;

/**
 * Created by ZhangGang on 2019/6/5.
 */
@Data
//
//采购单：请求类
public class PurchaseRequest {
    private double amount;  //采购金额
    private int number;  //采购单编号
    private String purpose;  //采购目的

    public PurchaseRequest(double amount, int number, String purpose) {
        this.amount = amount;
        this.number = number;
        this.purpose = purpose;
    }

}
