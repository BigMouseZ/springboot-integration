package com.integration.create.builder;

/**
 * Created by Administrator on 2019/6/4 0004.
 */
//游戏角色创建控制器：指挥者
public class ActorController {
    //逐步构建复杂产品对象

    public Actor construct(ActorBuilder ab) {

        Actor actor;

        ab.buildType();

        ab.buildSex();

        ab.buildFace();

        ab.buildCostume();

        ab.buildHairstyle();

        actor = ab.createActor();

        return actor;

    }
}
