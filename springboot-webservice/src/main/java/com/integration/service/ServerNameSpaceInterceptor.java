package com.integration.service;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.ServiceInfo;

/**
 * Created by ZhangGang on 2019/6/13.
 */
public class ServerNameSpaceInterceptor extends AbstractPhaseInterceptor<Message> {

    public ServerNameSpaceInterceptor()
    {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        for (ServiceInfo si : message.getExchange().getService().getServiceInfos()) {
            si.setProperty("soap.force.doclit.bare",true); //这个就是忽略客户端不带命名空间的关键
        }
    }
}
