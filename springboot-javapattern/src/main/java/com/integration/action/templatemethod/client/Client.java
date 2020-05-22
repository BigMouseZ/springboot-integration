package com.integration.action.templatemethod.client;

import com.integration.action.templatemethod.Account;
import com.integration.action.templatemethod.DataViewer;
import com.integration.action.templatemethod.impl.CurrentAccount;
import com.integration.action.templatemethod.impl.SavingAccount;
import com.integration.action.templatemethod.impl.XMLDataViewer;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String[] args) {
        Account account = new CurrentAccount();
        Account account1 = new SavingAccount();
        DataViewer dv = new XMLDataViewer();
        dv.Process();

        //读取配置文件
      /*  String subClassStr = ConfigurationManager.AppSettings["subClass"];
        //反射生成对象
        account = (Account)Assembly.Load("TemplateMethodSample").CreateInstance(subClassStr);*/
        account.Handle("张无忌", "123456");
        account1.Handle("张无忌", "123456");

    }

}
