package com.lombok.at_RequiredArgsConstructor;

import com.sun.istack.internal.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by ZhangGang on 2018/11/28.
 *生成构造方法（可能带参数也可能不带参数），如果带参数，这参数只能是以final修饰的未经初始化的字段，或者是以@NonNull注解的未经初始化的字段。
 @RequiredArgsConstructor(staticName = “of”)会生成一个of()的静态方法，并把构造方法设置为私有的
 */
@RequiredArgsConstructor
public class User {
    private Long id;

    @NonNull
    private String phone;

    @NotNull
    private Integer status = 0;

    private final Integer age;
    private final String country = "china";
}

