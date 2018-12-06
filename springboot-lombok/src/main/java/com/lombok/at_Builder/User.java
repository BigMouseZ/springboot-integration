package com.lombok.at_Builder;

import lombok.Builder;
import lombok.experimental.Tolerate;

/**
 * Created by ZhangGang on 2018/11/28.
 * @Builder 注释为你的类生成复杂的构建器API。
 * builder模式会不创建无参构造器，在做Jackson转换时需要用到无参构造器
 * 加上@Tolerate 创建
 *
 */
@Builder
public class User {
    private Long id;
    private String phone;

    @Tolerate
     public User() {
             }
}

