package com.integration.action.observer;

/**
 * Created by Administrator on 2019/6/8 0008.
 */


//抽象观察者类
public interface Observer {
    public String getName();
    public void setName(String name);
    public void help(); //声明支援盟友方法
    public void beAttacked(AllyControlCenter acc); //声明遭受攻击方法
}






