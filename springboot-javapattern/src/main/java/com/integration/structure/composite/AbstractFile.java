package com.integration.structure.composite;

/**
 * Created by ZhangGang on 2019/6/5.
 */


//抽象文件类：抽象构件
public abstract class AbstractFile {
    public abstract void add(AbstractFile file);
    public abstract void remove(AbstractFile file);
    public abstract AbstractFile getChild(int i);
    public abstract void killVirus();
}