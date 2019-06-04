package com.integration.create.builder.impl;

import com.integration.create.builder.ActorBuilder;

/**
 * Created by Administrator on 2019/6/4 0004.
 */
//恶魔角色建造器：具体建造者
public class DevilBuilder extends ActorBuilder {

    public void buildType() {

        actor.setType("恶魔");

    }

    public void buildSex() {

        actor.setSex("妖");

    }

    public void buildFace() {

        actor.setFace("丑陋");

    }

    public void buildCostume() {

        actor.setCostume("黑衣");

    }

    public void buildHairstyle() {

        actor.setHairstyle("光头");

    }

}
