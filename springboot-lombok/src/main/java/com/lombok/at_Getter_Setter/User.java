package com.lombok.at_Getter_Setter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ZhangGang on 2018/11/28.
 * id字段生成了Getter&Setter,访问修饰符是public
 * phone只生成了Getter方法，因为只使用了@Getter而没有使用@Setter, 并且访问修饰符是protected
 * password 上并没有注解，所以什么都不生成
 * 注意：Lombok中的注解一般都会包含一个无参构造函数注解@NoArgsConstructor(用于生成无参构造函数的) ，所以还会额外生成一个无参构造函数
 *
 * @Getter @Setter 注解在类上，表示为类中的所有字段生成Getter&Setter方法。
 * @Getter(lazy = true)
标注字段为懒加载字段，懒加载字段在创建对象时不会进行初始化，而是在第一次访问的时候才会初始化，后面再次访问也不会重复初始化
 */
public class User {
    @Getter
    @Setter
    private Long id;

    @Getter(AccessLevel.PROTECTED)
    private String phone;

    private String password;
}
