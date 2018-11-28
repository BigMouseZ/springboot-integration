package com.lombok.at_NoArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by ZhangGang on 2018/11/28.
 *生成一个无参构造方法。当类中有final字段没有被初始化时，编译器会报错，此时可用@NoArgsConstructor(force = true)，
 * 然后就会为没有初始化的final字段设置默认值 0 / false / null, 这样编译器就不会报错。
 * 对于具有约束的字段（例如@NonNull字段），不会生成检查或分配，
 * 因此请注意，正确初始化这些字段之前，这些约束无效。
 */
@NoArgsConstructor(force = true)
public class User {

    private Long id;

    @NonNull
    private String phone;

    private String password;
}
