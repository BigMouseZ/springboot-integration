package com.integration.structure.facade.impl;

import com.integration.structure.facade.AbstractEncryptFacade;
import com.integration.structure.facade.subobject.FileReader;
import com.integration.structure.facade.subobject.FileWriter;
import com.integration.structure.facade.subobject.NewCipherMachine;

/**
 * Created by ZhangGang on 2019/6/5.
 */
//新增具体加密外观类NewEncryptFacade
public class NewEncryptFacade extends AbstractEncryptFacade {
    private FileReader reader;
    private NewCipherMachine cipher;
    private FileWriter writer;

    public NewEncryptFacade() {
        reader = new FileReader();
        cipher = new NewCipherMachine();
        writer = new FileWriter();
    }

    //调用其他对象的业务方法
    public void FileEncrypt(String fileNameSrc, String fileNameDes) {
        String plainStr = reader.Read(fileNameSrc);
        String encryptStr = cipher.Encrypt(plainStr);
        writer.Write(encryptStr, fileNameDes);
    }


}
