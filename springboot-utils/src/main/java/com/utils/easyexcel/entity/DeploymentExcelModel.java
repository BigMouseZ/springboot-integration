package com.utils.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/12/1
 *  
 * @name: 批量添加布控规则Excel模版的映射字段类
 *
 * @Description:
 *               1.多个任务可以共用此类，如非必须，不要修改此类。
 *               2.如果差异较大，可以在自己任务的包下新建独立的类
 */
@Data
public class DeploymentExcelModel extends BaseRowModel {
    @ExcelProperty(index = 0)
    private String carno;
    @ExcelProperty(index = 1)
    private String carnocolor;
    @ExcelProperty(index = 2)
    private String models;
    @ExcelProperty(index = 3)
    private String manufacturer;
    @ExcelProperty(index = 4)
    private String carcolor;
    @ExcelProperty(index = 5)
    private String handlingcompany;
    @ExcelProperty(index = 6)
    private String deployment;
    @ExcelProperty(index = 7)
    private String contact;
    @ExcelProperty(index = 8)
    private String enabletime;
    @ExcelProperty(index = 9)
    private String disabledtime;
    @ExcelProperty(index = 10)
    private String deploymenttype;
    @ExcelProperty(index = 11)
    private String deploymentcontent;
}
