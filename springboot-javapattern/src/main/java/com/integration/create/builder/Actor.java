package com.integration.create.builder;

import lombok.Data;

/**
 * Created by ZhangGang on 2019/6/4.
 * Actor角色类：复杂产品，考虑到代码的可读性，只列出部分成员属性，且成员属性的类型均为String，
 * 真实情况下，有些成员属性的类型需自定义
 */
@Data
public class Actor {
    private String type; //角色类型

    private String sex; //性别

    private String face; //脸型

    private String costume; //服装

    private String hairstyle; //发型
}
