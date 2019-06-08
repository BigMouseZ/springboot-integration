package com.integration.action.visitor.impl;

import com.integration.action.visitor.Department;
import com.integration.action.visitor.Employee;
import lombok.Data;

/**
 * Created by Administrator on 2019/6/8 0008.
 */

//全职员工类：具体元素类
@Data
public class FulltimeEmployee implements Employee {
    private String name;
    private double weeklyWage;
    private int workTime;

    public FulltimeEmployee(String name, double weeklyWage, int workTime) {
        this.name = name;
        this.weeklyWage = weeklyWage;
        this.workTime = workTime;
    }

    public void accept(Department handler) {
        handler.visit(this); //调用访问者的访问方法
    }
}
