package com.integration.create.prototype.shadowclone;

import lombok.Data;

/**
 * Created by ZhangGang on 2019/6/4.
 */
@Data
public class WeeklyLog implements Cloneable {
    private Attachment attachment;
    private String name;
    private String date;
    private String content;
    //使用clone()方法实现浅克隆
    public WeeklyLog clone()
    {
        Object obj = null;
        try {
            obj = super.clone();
            return (WeeklyLog) obj;
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制！");
            return null;
        }
    }

}
