package com.utils;

import com.utils.easyexcel.ExcelHandleUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyExcelTest {
    @Autowired
    private ExcelHandleUtil excelHandleUtil;
    @Test
    public void setFastDFSTest(){
        String path = excelHandleUtil.outputExcelByModule();


        System.out.println(getClass().getResource("/").getPath());

       // System.out.println("保存的路径："+path);
    }
}
