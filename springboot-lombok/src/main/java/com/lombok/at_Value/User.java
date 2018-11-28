package com.lombok.at_Value;


import lombok.Value;

/**
 * Created by ZhangGang on 2018/11/28.
 *@Value 将字段都变成不可变类型:使用final修饰，
 * 同时还包含@ToString、@EqualsAndHashCode、@AllArgsConstructor 、@Getter(注意只有Getter没有Setter)
 */
@Value
public class User {
    private Long id;
    private String username;
}



