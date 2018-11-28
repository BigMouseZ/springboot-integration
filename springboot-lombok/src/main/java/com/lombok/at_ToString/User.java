package com.lombok.at_ToString;

import lombok.ToString;

/**
 * Created by ZhangGang on 2018/11/28.
 *生成toString()方法，默认情况下它会按顺序（以逗号分隔）打印你的类名称以及每个字段。
 * 可以这样设置不包含哪些字段,可以指定一个也可以指定多个@ToString(exclude = “id”) / @ToString(exclude = {“id”,”name”})
 如果继承的有父类的话，可以设置callSuper 让其调用父类的toString()方法，例如：@ToString(callSuper = true)
 */
@ToString(exclude = {"password", "salt"})
public class User {

    private Long id;

    private String phone;

    private String password;

    private String salt;
}


