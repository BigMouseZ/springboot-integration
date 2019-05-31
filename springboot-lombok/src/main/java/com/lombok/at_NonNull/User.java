package com.lombok.at_NonNull;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by ZhangGang on 2018/11/28.
 *为字段赋值时(即调用字段的setter方法时)，如果传的参数为null，则会抛出空异常NullPointerException，生成setter方法时会对参数是否为空检查
 */
@Getter
@Setter
public class User {

    private Long id;

    @NonNull
    private String phone;

    private String password;
}
