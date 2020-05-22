package com.integration.springbootmq.mq;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonPassUtil {


	private  static  Gson simpgson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
	public static String objectToJson(Object ojb) {
		return simpgson.toJson(ojb);
	}

	public static <T> T josnToObject(String json, Class<T> cls) {
		return simpgson.fromJson(json, cls);
	}

	public static <T> List<T> jsonToList(String json, Class<T> cl) {
		List<T> mlist = new ArrayList<T>();
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
	    for(final JsonElement elem : array){
	        mlist.add(simpgson.fromJson(elem, cl));
	    }
	    return mlist;
	}

	public static <T> List<Map<String, T>> jsonToListMap(String json) {
		return simpgson.fromJson(json, new TypeToken<List<Map<String, T>>>() {}.getType());
	}

	public static <T> Map<String, T> jsonToMap(String json) {
		return simpgson.fromJson(json, new TypeToken<Map<String, T>>() {}.getType());
	}


}
