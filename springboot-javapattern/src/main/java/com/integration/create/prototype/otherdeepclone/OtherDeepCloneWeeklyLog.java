package com.integration.create.prototype.otherdeepclone;

import lombok.Data;

/**
 * Created by ZhangGang on 2019/6/4.
 */
@Data
public class OtherDeepCloneWeeklyLog implements Cloneable {
    private OtherDeepCloneAttachment attachment;
    private String name;
    private String date;
    private String content;
    //使用Clone技术实现深克隆
    public OtherDeepCloneWeeklyLog otherDeepClone() throws Exception {
        OtherDeepCloneWeeklyLog otherDeepCloneWeeklyLog =(OtherDeepCloneWeeklyLog) super.clone();;
        otherDeepCloneWeeklyLog.attachment = (OtherDeepCloneAttachment)attachment.clone();
        return otherDeepCloneWeeklyLog;
    }
}
