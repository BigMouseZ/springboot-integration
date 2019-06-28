package com.integration.structure.facade.client;

import com.integration.structure.facade.AbstractEncryptFacade;
import com.integration.structure.facade.impl.EncryptFacade;
import com.integration.structure.facade.impl.NewEncryptFacade;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String args[]) {
        AbstractEncryptFacade ef = new EncryptFacade();
        AbstractEncryptFacade newef = new NewEncryptFacade();

        String fileNameSrc = "D:\\signalway\\TestEntity\\src.txt";
        String fileNameDes = "D:\\signalway\\TestEntity\\des.txt";
        String newfileNameDes = "D:\\signalway\\TestEntity\\newdes.txt";
        ef.FileEncrypt(fileNameSrc, fileNameDes);
        newef.FileEncrypt(fileNameSrc, newfileNameDes);
        System.out.println("执行完毕！");
    }
}


