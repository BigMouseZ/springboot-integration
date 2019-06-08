package com.integration.action.visitor.impl;

import com.integration.action.visitor.Department;
import com.integration.action.visitor.Employee;
import lombok.Data;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
//兼职员工类：具体元素类
@Data
public class ParttimeEmployee implements Employee {
    private String name;
    private double hourWage;
    private int workTime;

    public ParttimeEmployee(String name, double hourWage, int workTime) {
        this.name = name;
        this.hourWage = hourWage;
        this.workTime = workTime;
    }


    public void accept(Department handler) {
        handler.visit(this); //调用访问者的访问方法
    }
}