package com.websocket.manage;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZhangGang on 2018/7/2.
 * 管理登录名与sessionid 的绑定
 */
public class LoginNameSessionIDMap {
    private static LoginNameSessionIDMap loginNameSessionIDMap = null;
    private static Object LOCK = new Object();

    public static Map<String,String> createloginNameSessionIDMap = new ConcurrentHashMap<>();
    public static LoginNameSessionIDMap instance(){
        synchronized (LOCK){
            if(loginNameSessionIDMap == null){
                return new LoginNameSessionIDMap();
            }
            return loginNameSessionIDMap;
        }
    }
    private LoginNameSessionIDMap(){

    }

    public void put(String key ,String value){
        createloginNameSessionIDMap.put(key,value);
    }
    
    public String get(String key){
        return createloginNameSessionIDMap.get(key);
    }
    public void clear(){
        createloginNameSessionIDMap.clear();
    }
    public boolean isEmpty(){
        return createloginNameSessionIDMap.isEmpty();
    }

    public String remove(String id) {
        return createloginNameSessionIDMap.remove(id);
    }

    public Collection<String> sessions() {
        return createloginNameSessionIDMap.values();
    }
}
