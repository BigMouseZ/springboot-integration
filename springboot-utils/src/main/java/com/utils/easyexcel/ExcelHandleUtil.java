package com.utils.easyexcel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.utils.easyexcel.entity.DeploymentExcelModel;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangGang on 2019/4/29.
 *
 * D:\ProgramData\GitHub\springboot-integration\springboot-utils\src\main\resources\templates
 */
@Component
public class ExcelHandleUtil {

    public  String outputExcelByModule(){
        //输出的文件路径
        File excelOutPutFile = new File(getClass().getResource("/").getPath() + "templates/" + System.currentTimeMillis() + "easyexcel输出数据模板.xlsx");
     //   StyleExcelHandler styleExcelHandler = new StyleExcelHandler();
        try {
            List<DeploymentExcelModel> modelList = new ArrayList<>();
                DeploymentExcelModel model = new DeploymentExcelModel();
                model.setCarno("车牌");
                model.setCarnocolor("蓝色");
                model.setModels("caew");
                model.setDeploymenttype("dsa");
                modelList.add(model);
            InputStream in = this.getClass().getResourceAsStream("/templates/批量添加布控模版.xlsx");
            OutputStream out = new FileOutputStream(excelOutPutFile);
            ExcelWriter writer = new ExcelWriter(in, out, ExcelTypeEnum.XLSX, false);
            Sheet sheet = new Sheet(0, 1, DeploymentExcelModel.class);
            sheet.setStartRow(0);
            writer.write(modelList, sheet);
            writer.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return excelOutPutFile.getAbsolutePath();

    }

    public static void main(String[] args) {
        ExcelHandleUtil aa = new ExcelHandleUtil();
       String path =  aa.outputExcelByModule();
        System.out.println(path);
    }

}
