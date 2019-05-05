package com.integration.service.impl;

import com.integration.service.HiKWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;


@WebService(targetNamespace = "http://service.thirdBayonet.webservice.bms.hikvision.com" )
public class HiKWebServiceImpl implements HiKWebService {

    private Logger logger = LoggerFactory.getLogger(HiKWebServiceImpl.class);


    @Override
    public String initSystem(String xml) {

        logger.info(xml);
        return "<?xml  version='1.0'  encoding='UTF-8'?>" +
                "<root>\n" +
                "<code>0</code>\n" +
                "\t<message>成功</message>\n" +
                "\t<sessionId>3e48331ca1c74e918315873bad3f26b3</sessionId>\n" +
                "</root>\n";
    }

    @Override
    public String insertVehicleInfo(String xml) {

        logger.info(xml);
        return "<?xml  version='1.0'  encoding='UTF-8'?>" +
                "<root>\n" +
                "<code>1</code>\n" +
                "\t<message>插入过车信息成功</message>\n" +
                "</root>\n";
    }
}
