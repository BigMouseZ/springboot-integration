package com.integration.structure.bridge.client;

import com.integration.structure.bridge.Image;
import com.integration.structure.bridge.ImageImp;
import com.integration.structure.bridge.impl.GIFImage;
import com.integration.structure.bridge.impl.LinuxImp;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String args[]) {
        Image image;
        ImageImp imp;
        image = new GIFImage();//(Image) XMLUtil.getBean("image");
        imp = new LinuxImp();//(ImageImp)XMLUtil.getBean("os");
        image.setImageImp(imp);
        image.parseFile("小龙女");
    }
}

