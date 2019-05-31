package com.integration.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ZhangGang on 2019/5/24.
 */
@Slf4j
@Component
public class WebServiceUtil {

    public String postWebService(String wsdlAddress, String namespcaeUrl, String method, String xml) {


        return null;
    }
    /**
     * @param wsdlAddress  wsdl 服务地址 http://33.192.75.150:5300/services/ThirdBayonetService 不带 =>   ？wsdl后缀
     * @param requestString 请求的报文字符串
     * @param header        请求的头
     * @return reponsexml 返回的xml报文
     * @Description 功能：通过http的方式调用webservice服务
     **/
    public String postWebServiceByHttp(String wsdlAddress, String requestString, Header header) {
        String reponsexml = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(wsdlAddress);
            StringEntity stringEntity = new StringEntity(requestString, "UTF-8");
            httpPost.addHeader(header);
            httpPost.setEntity(stringEntity);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000)
                    .setConnectionRequestTimeout(10000)
                    .setSocketTimeout(60000)
                    .build();
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            if (response != null) {
                reponsexml = EntityUtils.toString(response.getEntity());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    log.info("WebServiceHttp请求成功!返回的状态码：" + response.getStatusLine().getStatusCode());
                    log.info("WebServiceHttp请求成功!返回的数据：" + reponsexml);
                } else {
                    log.info("WebServiceHttp请求失败：返回的状态码：" + response.getStatusLine().getStatusCode());
                    log.info("WebServiceHttp请求失败，返回的数据：" + reponsexml);
                    return "";
                }
            } else {
                log.info("WebServiceHttp请求失败!");
                return "";
            }
        } catch (IOException e) {
            log.error("WebServiceHttp请求失败：", e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("关闭httpclient失败：", e);
            }
        }
        return reponsexml;
    }
}
