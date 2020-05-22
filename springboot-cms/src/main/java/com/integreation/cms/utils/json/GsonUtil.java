package com.integreation.cms.utils.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/12/16.
 */
public class GsonUtil {
    public static String createGsonString(Object object) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        String gsonString = gson.toJson(object);
        return gsonString;
    }

    public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        T t = gson.fromJson(gsonString, cls);
        return t;
    }

    public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }


    public static <T> List<Map<String, T>> changeGsonToListMaps(
            String gsonString) {
        List<Map<String, T>> list = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
        Map<String, T> map = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }

    public static <T> T ConvertToObject (String json, TypeReference valueTypeRef)
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略不需要字段
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        T object = null;
        try {
            object = (T)mapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            System.out.println("有异常的Json： " + json);
            e.printStackTrace();
        }
        return object;
    }
}
