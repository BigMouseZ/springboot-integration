package com.integration.structure.facade.impl;

import com.integration.structure.facade.AbstractEncryptFacade;
import com.integration.structure.facade.subobject.CipherMachine;
import com.integration.structure.facade.subobject.FileReader;
import com.integration.structure.facade.subobject.FileWriter;

/**
 * Created by ZhangGang on 2019/6/5.
 */
public class EncryptFacade extends AbstractEncryptFacade {
    private FileReader reader;
    private CipherMachine cipher;
    private FileWriter writer;

    public EncryptFacade() {
        reader = new FileReader();
        cipher = new CipherMachine();
        writer = new FileWriter();
    }

    //调用其他对象的业务方法
    public void FileEncrypt(String fileNameSrc, String fileNameDes) {
        String plainStr = reader.Read(fileNameSrc);
        String encryptStr = cipher.Encrypt(plainStr);
        writer.Write(encryptStr, fileNameDes);
    }


}
