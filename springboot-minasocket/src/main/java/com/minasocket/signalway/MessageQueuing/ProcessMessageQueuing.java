package com.minasocket.signalway.MessageQueuing;

import com.minasocket.signalway.entity.BasicReturn;
import com.minasocket.signalway.entity.questentity.SendCommandPojo;
import com.minasocket.signalway.entity.questentity.SendFilePojo;
import com.minasocket.signalway.entity.rabbitmq.ProcessObject;
import com.minasocket.signalway.entity.rabbitmq.ReceiveQueuingObject;
import com.minasocket.signalway.entity.rabbitmq.ReturnQueuingObject;
import com.minasocket.signalway.service.MaintenanceService;
import com.utils.jsonutils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Admin on 2016/12/16.
 */

@Component
public class ProcessMessageQueuing {
    private static final Logger logger = LoggerFactory.getLogger(ProcessMessageQueuing.class);

    @Autowired
    MaintenanceService maintenanceService;
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
                pojo = GsonUtil.changeGsonToBean(ro.data, SendCommandPojo.class);
                //逻辑处理
                returnStr = maintenanceService.GetOBCFile((SendCommandPojo) pojo);
                processObject.setReturn(true);
                processObject.setStrReturn(returnStr);
                break;
            case "PoliceMaintenance.SendOrder":
                pojo = GsonUtil.changeGsonToBean(ro.data, SendCommandPojo.class);
                //逻辑处理
                returnStr = maintenanceService.SendCommand((SendCommandPojo) pojo);
                processObject.setReturn(true);
                processObject.setStrReturn(returnStr);
                break;
            case "DeviceMaintenance.SendFile":
                pojo = GsonUtil.changeGsonToBean(ro.data, SendFilePojo.class);
                //逻辑处理
                returnStr = maintenanceService.SendFile((SendFilePojo) pojo);
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
