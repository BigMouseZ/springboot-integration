package com.integration;

import com.starit.SoapClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/11/26
 *  
 * @name: 调用webServicer的单例类
 *
 * @Description: 
 */
public class SoapClientManager {

    private Logger logger = LoggerFactory.getLogger(SoapClientManager.class);

    private volatile static SoapClientManager soapClientManager;

    private final int queueCount = 5;

    private LinkedBlockingQueue<SoapClient> queue = new LinkedBlockingQueue(queueCount);


    private SoapClientManager(){

        SoapClient client = new SoapClient();
        try {
            queue.put(client);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e.toString(),e);
        }

    }

    public static SoapClientManager instance(){
        if(soapClientManager == null){
            synchronized (SoapClientManager.class) {
                if(soapClientManager == null){
                    soapClientManager = new SoapClientManager();
                }
            }
        }
        return soapClientManager;
    }

    public Map<String, String> send(String operation, Map<String, Object> params, String wsdlUrl) throws Exception {

        SoapClient client = null;
        try {

            client = queue.take();
            if (client == null) {
                return null;
            }
            return client.sendRequest(operation,params,wsdlUrl);

        } finally {
            if (client != null) {
                queue.put(client);
            }
        }


    }

    public Map<String, String> send(String address,String operation, Map<String, Object> params, String wsdlUrl) throws Exception {

        SoapClient client = null;
        try {

            client = queue.take();
            if (client == null) {
                return null;
            }
            return client.sendRequest(address,operation,params,wsdlUrl);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(),e);

        }finally {
            if (client != null) {
                queue.put(client);
            }
        }

        return null;
    }

    /**
     * 调用的示例
     * @param method
     * @param jkgnh
     * @param jkxlh
     * @param content
     * @param wsdl
     */
/*
    public void test(String method,String jkgnh,String jkxlh,String content,String wsdl){
        Map<String, String> resultMap = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(method + ".jkgnh", jkgnh);
            params.put(method + ".jkxlh", jkxlh);
            params.put(method + ".WriteXmlDoc", content);

            resultMap = SoapClientManager.instance().send(method, params, wsdl);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(),e);
        }

        if (resultMap == null) {
            return ;
        }

        String xml = resultMap.get("WriteVioDataToDbResponse.WriteVioDataToDbResult");
        if (StringUtils.isBlank(xml)) {
            Iterator<String> iterator = resultMap.keySet().iterator();
            xml = resultMap.get(iterator.next());
        }
    }
*/



}
