package com.integration.create.builder;

import lombok.Data;

/**
 * Created by ZhangGang on 2019/6/4.
 */
@Data
public class Product {
    private  String partA; //定义部件，部件可以是任意类型，包括值类型和引用类型

    private  String partB;

    private  String partC;
}
