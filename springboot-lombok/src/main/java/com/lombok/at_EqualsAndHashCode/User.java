package com.lombok.at_EqualsAndHashCode;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by ZhangGang on 2018/11/28.
 *生成hashCode()和equals()方法，默认情况下，它将使用所有非静态，非transient字段。但可以通过在可选的exclude参数中来排除更多字段。或者，通过在of参数中命名它们来准确指定希望使用哪些字段。
 // exclude 排除字段
 @EqualsAndHashCode(exclude = {“password”, “salt”})
 // of 指定要包含的字段
 @EqualsAndHashCode(of = {“id”, “phone”, “password”})
 */
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable {

    private static final long serialVersionUID = 6569081236403751407L;

    private Long id;

    private String phone;

    private transient int status;
}


