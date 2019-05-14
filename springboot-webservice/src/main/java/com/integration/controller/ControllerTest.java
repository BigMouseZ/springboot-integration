package com.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by ZhangGang on 2019/5/6.
 */
@RestController
public class ControllerTest {
    private  static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
    /**
     * @param reqString 接口json穿串
     * @throws IOException IO异常
     * @Description 功能：
     * @return strung。
     **/
    @RequestMapping(value = "/service/upload_pic.php",method = RequestMethod.POST)
    public String helloController(String puid,String chan,
                                  String zptime,String faceid,
                                  String acescore,String x,
                                  String y,String width,
                                  String height,String age,
                                  String sex,String glass,
                                  String end,String stay,
                                  String picnum,String gpsEW,
                                  String longitude,String gpsNS,
                                  String latitude,String remark,HttpURLConnection httpUrlCon) throws IOException {
       /*   级别依次为【从高到低】：FATAL > ERROR > WARN > INFO > DEBUG > TRACE  */
        logger.info("error 日志测试！helloController:"+chan);
        System.out.println("Request Headers:");
        System.out.println(" " + httpUrlCon.getRequestMethod() + " / " + " HTTP/1.1");
        System.out.println(" Host: " + httpUrlCon.getRequestProperty("Host"));
        System.out.println(" Connection: " + httpUrlCon.getRequestProperty("Connection"));
        System.out.println(" Accept: " + httpUrlCon.getRequestProperty("Accept"));
        System.out.println(" User-Agent: " + httpUrlCon.getRequestProperty("User-Agent"));
        System.out.println(" Accept-Encoding: " + httpUrlCon.getRequestProperty("Accept-Encoding"));
        System.out.println(" Accept-Language: " + httpUrlCon.getRequestProperty("Accept-Language"));
        System.out.println(" Cookie: " + httpUrlCon.getRequestProperty("Cookie"));
        System.out.println(" Connection: " + httpUrlCon.getHeaderField("Connection"));//利用另一种读取HTTP头字段
        System.out.println();

        return  "testsuccess";
    }
}
