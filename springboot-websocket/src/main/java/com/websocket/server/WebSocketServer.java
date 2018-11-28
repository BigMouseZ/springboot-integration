package com.websocket.server;

import com.utils.jsonutils.GsonUtil;
import com.websocket.manage.LoginNameSessionIDMap;
import com.websocket.manage.WebSocketSessionMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ZhangGang on 2018/11/26.
 */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    @Value("${mina.socket.host}")
    private String host;
    @Value("${mina.socket.port}")
    private int port;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        log.info("打开一个新的连接");
       /*当用户量多的时候可以异步创建连接*/
        WebSocketSessionMap.instance().put(session.getId(),session);
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
//        log.info("收到来自窗口"+sid+"的信息:"+message);
        log.info("WebSocket接收的字符消息[" + message + "].");
        log.info("sessionid="+session.getId());
        if("心跳".equals(message)){
            log.info("收到ws心跳");
            return;
        }
        /*
        * `{
            "sessionId": "${localStorage.sessionId}",
            "id": "${localStorage.sessionId}",
            "command": "WS.LOGIN",
            "params": {}
          }`
        * */
        Map<String, Object> map= GsonUtil.changeGsonToMaps(message);
        if("WS.LOGIN".equals(map.get("command"))){
            //登录，根据sessionid去缓存拿 loginname
            String loginname = "loginname";
            LoginNameSessionIDMap.instance().put(loginname,session.getId());
        }

        /*异步处理消息*/
    }
    /**
     * @param session
     */
    @OnError
    public void onError(Session session,Throwable ex) {
        log.info("sessionid="+session.getId());
        //移除缓存
        try {
            WebSocketSessionMap.instance().remove(session.getId()+"");
            for (Map.Entry<String,String> one: LoginNameSessionIDMap.createloginNameSessionIDMap.entrySet()){
                if(one.getValue().equals(session.getId()+"")){
                    LoginNameSessionIDMap.instance().remove(one.getKey());
                    break;
                }
            }
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ex.printStackTrace();
       log.error("发生错误");
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        WebSocketSessionMap.instance().remove(session.getId()+"");
        for (Map.Entry<String,String> one: LoginNameSessionIDMap.createloginNameSessionIDMap.entrySet()){
            if(one.getValue().equals(session.getId()+"")){
                LoginNameSessionIDMap.instance().remove(one.getKey());
                break;
            }
        }
        //异步关闭消息
        WebSocketSessionMap.instance().remove(session.getId());
        log.info("有一连接关闭！");
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Session session,String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }
}
