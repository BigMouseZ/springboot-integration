package com.wxpay.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/12/16.
 */
public class GsonUtil {
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
    private static Gson gsonWithoutNull = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static Gson gsonWithMsec = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").serializeNulls().create();
    private static Exception ex = null;

    public static String createGsonString(Object object) {
        String gsonString = gson.toJson(object);
        return gsonString;
    }
    public static String createGsonStringWithoutNull(Object object) {
        return gsonWithoutNull.toJson(object);
    }

    public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
        try {
            T t = gson.fromJson(gsonString, cls);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            ex = e;
        }
        return null;
    }
    public static <T> T changeGsonToBeanWithMsec(String gsonString, Class<T> cls) {
        try {
            T t = gsonWithMsec.fromJson(gsonString, cls);
            return t;
        } catch (Exception e) {
            try {
                T t = gson.fromJson(gsonString, cls);
                return t;
            } catch (Exception eIn) {
                eIn.printStackTrace();
                ex = eIn;
            }
//            e.printStackTrace();
//            ex = e;
        }
        return null;
    }

    public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
        try {
            List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            ex = e;
        }
        return null;
    }

    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }


    public static <T> List<Map<String, T>> changeGsonToListMaps(
            String gsonString) {
        List<Map<String, T>> list = null;
        list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
        Map<String, T> map = null;
        try {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            ex = e;
        }
        return map;
    }
    public static Exception getEx(){
        return ex;
    }


}
