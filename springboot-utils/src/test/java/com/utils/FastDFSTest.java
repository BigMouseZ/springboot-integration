package com.utils;

import com.utils.fastdfs.FastFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FastDFSTest {
    @Autowired
    private FastFileUtil fastFileUtil;
    @Test
    public void setFastDFSTest(){
        System.out.println("上传文件路径："+ fastFileUtil.uploadFile(new File("E:\\20171101194330.jpg")));
    }
}
