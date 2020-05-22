package com.integration.structure.flyweight;

/**
 * Created by ZhangGang on 2019/6/5.
 */

//围棋棋子类：抽象享元类
public abstract class IgoChessman {
    public abstract String getColor();

    public void display() {
        System.out.println("棋子颜色：" + this.getColor());
    }
    public void display(Coordinates coord){
        System.out.println("棋子颜色：" + this.getColor() + "，棋子位置：" + coord.getX() + "，" + coord.getY() );
    }

}





