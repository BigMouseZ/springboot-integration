package com.integration.structure.bridge.impl;

import com.integration.structure.bridge.Image;
import com.integration.structure.bridge.Matrix;

/**
 * Created by ZhangGang on 2019/6/5.
 */
//GIF格式图像：扩充抽象类
public class GIFImage extends Image {
    public void parseFile(String fileName) {
        //模拟解析GIF文件并获得一个像素矩阵对象m;
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + "，格式为GIF。");
    }
}
