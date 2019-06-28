package com.integration.action.templatemethod;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
//钩子方法的引入使得子类可以控制父类的行为
public abstract class DataViewer {
    //抽象方法：获取数据
    public abstract void GetData();

    //具体方法：转换数据
    public void ConvertData() {
        System.out.println("将数据转换为XML格式。");
    }

    //抽象方法：显示数据
    public abstract void DisplayData();

    //钩子方法：判断是否为XML格式的数据
    public abstract boolean IsNotXMLData();

    //模板方法
    public void Process() {
        GetData();
        //如果不是XML格式的数据则进行数据转换
        if (IsNotXMLData()) {
            ConvertData();
        }
        DisplayData();
    }

}
