package com.integration.action.iterator.impl;

import com.integration.action.iterator.AbstractIterator;
import com.integration.action.iterator.AbstractObjectList;

import java.util.List;

/**
 * Created by ZhangGang on 2019/6/5.
 */
 //商品数据类：具体聚合类
public class ProductList extends AbstractObjectList {
    public ProductList(List products) {
        super(products);
    }

    //实现创建迭代器对象的具体工厂方法
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}
