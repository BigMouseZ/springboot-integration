package com.websocket.websocketserver.manage;

import javax.websocket.Session;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZhangGang on 2018/7/2.
 * 管理sessionid
 */
public class WebSocketSessionMap {
    private static WebSocketSessionMap socketSessionMap = null;
    private static Object LOCK = new Object();

    public static Map<String,Session> createSocketSessionMap =new ConcurrentHashMap<String,Session>();

    public static WebSocketSessionMap instance(){
        synchronized (LOCK){
            if(socketSessionMap == null){
                return new WebSocketSessionMap();
            }
            return socketSessionMap;
        }
    }
    private WebSocketSessionMap(){

    }

    public void put(String key ,Session value){
        createSocketSessionMap.put(key,value);
    }
    public Session get(String key){
        return createSocketSessionMap.get(key);
    }
    public void clear(){
        createSocketSessionMap.clear();
    }
    public boolean isEmpty(){
        return createSocketSessionMap.isEmpty();
    }

    public Session remove(String id) {
        return createSocketSessionMap.remove(id);
    }

    public Collection<Session> sessions() {
        return createSocketSessionMap.values();
    }
}
