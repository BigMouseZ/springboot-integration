package com.integration.action.strategy.client;

import com.integration.create.builder.Actor;
import com.integration.create.builder.ActorBuilder;
import com.integration.create.builder.ActorController;
import com.integration.create.builder.impl.AngelBuilder;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args) {
        ActorBuilder ab = new AngelBuilder();//针对抽象建造者编程
                //(ActorBuilder) XMLUtil.getBean(); // 反射生成具体建造者对象
        ActorController ac = new ActorController();
       // Actor actor = ac.construct(ab); //通过指挥者创建完整的建造者对象
        Actor actor = ab.construct();  //由建造者直接完成指挥者工作
        String type = actor.getType();

        System.out.println(type + "的外观：");

        System.out.println("性别：" + actor.getSex());

        System.out.println("面容：" + actor.getFace());

        System.out.println("服装：" + actor.getCostume());

        System.out.println("发型：" + actor.getHairstyle());
    }
}
