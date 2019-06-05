package com.integration.structure.flyweight;

import lombok.Data;

/**
 * Created by ZhangGang on 2019/6/5.
 */

//坐标类：外部状态类
@Data
public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
