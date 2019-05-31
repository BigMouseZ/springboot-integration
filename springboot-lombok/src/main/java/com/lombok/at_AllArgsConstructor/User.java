package com.lombok.at_AllArgsConstructor;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Created by ZhangGang on 2018/11/28.
 *生成一个全参数的构造方法
 */
@AllArgsConstructor
public class User {
    private Long id;

    @NonNull
    private String phone;

    @NotNull
    private Integer status = 0;

    private final Integer age;
    private final String country = "china";
}

