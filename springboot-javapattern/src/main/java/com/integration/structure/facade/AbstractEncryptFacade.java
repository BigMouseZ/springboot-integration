package com.integration.structure.facade;

/**
 * Created by ZhangGang on 2019/6/5.
 */
//抽象外观类，客户端针对抽象外观类编程
public abstract class AbstractEncryptFacade {
    public abstract void FileEncrypt(String fileNameSrc, String fileNameDes);
}
