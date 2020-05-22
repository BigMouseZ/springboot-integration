package com.integration.create.prototype.client;

import com.integration.create.prototype.deepclone.DeepCloneAttachment;
import com.integration.create.prototype.deepclone.DeepCloneWeeklyLog;
import com.integration.create.prototype.otherdeepclone.OtherDeepCloneAttachment;
import com.integration.create.prototype.otherdeepclone.OtherDeepCloneWeeklyLog;
import com.integration.create.prototype.shadowclone.Attachment;
import com.integration.create.prototype.shadowclone.WeeklyLog;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args)  {

         shadowClone();
        deepClone();
        otherDeepClone();
    }
    /*
    *  由于使用的是浅克隆技术，因此工作周报对象复制成功，通过“==”比较原型对象和克隆对象的内存地址时输出false；
    *  但是比较附件对象的内存地址时输出true，说明它们在内存中是同一个对象。
    * */
    private static void shadowClone() {
        WeeklyLog log_previous, log_new;
        log_previous = new WeeklyLog(); //创建原型对象
        Attachment attachment = new Attachment(); //创建附件对象
        log_previous.setAttachment(attachment); //将附件添加到周报中
        log_new = log_previous.clone(); //调用克隆方法创建克隆对象
        //比较周报
        System.out.println("shadowClone周报是否相同？ " + (log_previous == log_new));
        //比较附件
        System.out.println("shadowClone附件是否相同？ " + (log_previous.getAttachment() == log_new.getAttachment()));
    }

    private static void deepClone() {
        DeepCloneWeeklyLog log_previous, log_new;
        log_previous = new DeepCloneWeeklyLog(); //创建原型对象
        DeepCloneAttachment attachment = new DeepCloneAttachment(); //创建附件对象
        log_previous.setAttachment(attachment); //将附件添加到周报中
        try {
            log_new = log_previous.deepClone(); //调用深克隆方法创建克隆对象
            //比较周报
            System.out.println("deepClone周报是否相同？ " + (log_previous == log_new));
            //比较附件
            System.out.println("deepClone附件是否相同？ " + (log_previous.getAttachment() == log_new.getAttachment()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("克隆失败！");
        }

    }
    private static void otherDeepClone() {
        OtherDeepCloneWeeklyLog log_previous, log_new;
        log_previous = new OtherDeepCloneWeeklyLog(); //创建原型对象
        OtherDeepCloneAttachment attachment = new OtherDeepCloneAttachment(); //创建附件对象
        log_previous.setAttachment(attachment); //将附件添加到周报中
        try {
            log_new = log_previous.otherDeepClone(); //调用深克隆方法创建克隆对象
            //比较周报
            System.out.println("otherDeepClone周报是否相同？ " + (log_previous == log_new));
            //比较附件
            System.out.println("otherDeepClone附件是否相同？ " + (log_previous.getAttachment() == log_new.getAttachment()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("克隆失败！");
        }

    }
}
