package com.integration.action.chainofresponsibility.client;

import com.integration.action.chainofresponsibility.Approver;
import com.integration.action.chainofresponsibility.PurchaseRequest;
import com.integration.action.chainofresponsibility.impl.Congress;
import com.integration.action.chainofresponsibility.impl.Director;
import com.integration.action.chainofresponsibility.impl.President;
import com.integration.action.chainofresponsibility.impl.VicePresident;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args) {
        Approver wjzhang, gyang, jguo, meeting;
        wjzhang = new Director("张无忌");
        gyang = new VicePresident("杨过");
        jguo = new President("郭靖");
        meeting = new Congress("董事会");

        //创建职责链
        wjzhang.setSuccessor(gyang);
        gyang.setSuccessor(jguo);
        jguo.setSuccessor(meeting);

        //创建采购单
        PurchaseRequest pr1 = new PurchaseRequest(45000, 10001, "购买倚天剑");
        wjzhang.processRequest(pr1);

        PurchaseRequest pr2 = new PurchaseRequest(60000, 10002, "购买《葵花宝典》");
        wjzhang.processRequest(pr2);

        PurchaseRequest pr3 = new PurchaseRequest(160000, 10003, "购买《金刚经》");
        wjzhang.processRequest(pr3);

        PurchaseRequest pr4 = new PurchaseRequest(800000, 10004, "购买桃花岛");
        wjzhang.processRequest(pr4);
    }
}
