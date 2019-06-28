package com.integration.action.templatemethod.impl;

import com.integration.action.templatemethod.DataViewer;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
public class XMLDataViewer extends DataViewer {
    @Override
    public void GetData() {
        System.out.println("从XML文件中获取数据。");
    }

    @Override
    public void DisplayData() {
        System.out.println("以柱状图显示数据。");

    }

    @Override
    public boolean IsNotXMLData() {
        return true;
    }
}
