package com.websocket.websocketserver.controller;

import com.websocket.websocketserver.manage.LoginNameSessionIDMap;
import com.websocket.websocketserver.manage.WebSocketSessionMap;
import com.websocket.websocketserver.server.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.io.IOException;

/**
 * Created by ZhangGang on 2018/8/20.
 */
@Slf4j
@RestController
public class Controller {
    @Autowired
    WebSocketServer webSocketServer;
    /**
     * @param reqString 接口json穿串
     * @throws IOException IO异常
     * @Description 功能：
     * @return strung。
     **/
    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String helloController(String reqString) throws IOException {
       /*   级别依次为【从高到低】：FATAL > ERROR > WARN > INFO > DEBUG > TRACE  */
        log.debug("debug 日志测试！helloController");
        log.info("info 日志测试！helloController");
        log.warn("warn 日志测试！helloController");
        log.error("error 日志测试！helloController");
        String sessionid =  LoginNameSessionIDMap.instance().get("loginname");
        Session session = WebSocketSessionMap.instance().get(sessionid);
        webSocketServer.sendMessage(session,"服务器消息");
        return  "测试成功";
    }
}
