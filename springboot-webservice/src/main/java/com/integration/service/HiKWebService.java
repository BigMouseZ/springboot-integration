package com.integration.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: 张刚
 * @Date: 2019/04/30
 * @name:
 * @Description: webservice服务
 */
@WebService(targetNamespace = "http://service.thirdBayonet.webservice.bms.hikvision.com")
public interface HiKWebService {


    @WebMethod
    String initSystem(@WebParam(name = "xml") String xml);
    @WebMethod
    String insertVehicleInfo (@WebParam(name = "xml") String xml);

}
