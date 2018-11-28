package com.lombok.at_Delegate;

import lombok.experimental.Delegate;

import java.util.List;

/**
 * Created by ZhangGang on 2018/11/28.
 *主要用来修饰 IO 流相关类, 会在 finally 代码块中对该资源进行 close();
 *
 */
public class User {
    @Delegate
    private List<String> address;
}

