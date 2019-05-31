package com.utils.MessageQueuing;

import com.utils.jsonutils.GsonUtil;
import com.utils.rabbitmqutils.entity.BasicReturn;
import com.utils.rabbitmqutils.entity.ProcessObject;
import com.utils.rabbitmqutils.entity.ReceiveQueuingObject;
import com.utils.rabbitmqutils.entity.ReturnQueuingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by Admin on 2016/12/16.
 */

@Component
public class ProcessMessageQueuing {
    private static final Logger logger = LoggerFactory.getLogger(ProcessMessageQueuing.class);

    public ProcessObject ProcessRequest(String message) {
        try {
            ProcessObject po = processCommand(message);
            return po;
        } catch (Exception e) {
            ProcessObject po = new ProcessObject();
            po.setReturn(true);
            po.setStrReturn(BasicReturn.ReturnEx(e));
            e.printStackTrace();
            return po;
        }
    }
    private ProcessObject processCommand(String receiveMessage) {
        ReceiveQueuingObject ro = GsonUtil.changeGsonToBean(receiveMessage, ReceiveQueuingObject.class);
        ProcessObject processObject = new ProcessObject();
        String command = ro.getCommand();
        String returnStr = "";
        Object pojo;
        switch (command){
            case "PoliceMaintenance.GetOBCFile":
                //逻辑处理
                returnStr = "MQ测试消息：PoliceMaintenance.GetOBCFile";
                processObject.setReturn(true);
                processObject.setStrReturn(returnStr);
                break;
            case "PoliceMaintenance.SendOrder":
                //逻辑处理
                returnStr = "MQ测试消息：PoliceMaintenance.SendOrder";
                processObject.setReturn(true);
                processObject.setStrReturn(returnStr);
                break;
            case "DeviceMaintenance.SendFile":
                //逻辑处理
                returnStr = "MQ测试消息：PoliceMaintenance.SendFile";
                processObject.setReturn(true);
                processObject.setStrReturn(returnStr);
                break;
            default:
                ReturnQueuingObject returnObject = new ReturnQueuingObject();
                returnObject.setErrorcode("3");
                returnObject.setMessage("没有对应的command");
                returnObject.setData("");
                returnStr = GsonUtil.createGsonString(returnObject);
                processObject.setReturn(true);
                processObject.setStrReturn(returnStr);
                break;
        }
        return processObject;
    }
}
