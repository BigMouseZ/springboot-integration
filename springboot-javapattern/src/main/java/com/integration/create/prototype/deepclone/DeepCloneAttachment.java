package com.integration.create.prototype.deepclone;

import java.io.Serializable;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class DeepCloneAttachment implements Serializable{
    private String name; //附件名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void download() {
        System.out.println("下载附件，文件名为" + name);
    }
}
