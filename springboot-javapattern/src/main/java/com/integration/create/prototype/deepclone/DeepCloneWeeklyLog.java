package com.integration.create.prototype.deepclone;

import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by ZhangGang on 2019/6/4.
 */
@Data
public class DeepCloneWeeklyLog implements Serializable {
    private DeepCloneAttachment attachment;
    private String name;
    private String date;
    private String content;
    /*
    *  在Java语言中，如果需要实现深克隆，可以通过序列化(Serialization)等方式来实现。
    *  序列化就是将对象写到流的过程，写到流中的对象是原有对象的一个拷贝，而原对象仍然存在于内存中。
    *  通过序列化实现的拷贝不仅可以复制对象本身，而且可以复制其引用的成员对象，因此通过序列化将对象写到一个流中，
    *  再从流里将其读出来，可以实现深克隆。需要注意的是能够实现序列化的对象其类必须实现Serializable接口，否则无法实现序列化操作

    * */
    //使用序列化技术实现深克隆
    public DeepCloneWeeklyLog deepClone() throws Exception {
        //将对象写入流中
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);
        //将对象从流中取出
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (DeepCloneWeeklyLog) ois.readObject();
    }
}
