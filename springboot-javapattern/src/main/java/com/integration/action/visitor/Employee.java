package com.integration.action.visitor;

/**
 * Created by Administrator on 2019/6/8 0008.
 */

//员工类：抽象元素类
public interface Employee
{
    public void accept(Department handler); //接受一个抽象访问者访问
}









