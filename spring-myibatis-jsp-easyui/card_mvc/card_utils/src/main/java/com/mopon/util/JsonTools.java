package com.mopon.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class JsonTools<T> {
	/**
	 * 将对象转换为json String
	 * 方法用途: <br>
	 * @param t
	 * @return
	 */
	public String ObjectToString(T t){
		return JSON.toJSONString(t);
	}
	
	/**
	 * 将json String转换为对象
	 * 方法用途: <br>
	 * @param str
	 * @param clazz
	 * @return
	 */
	public T  StringToObject(String str,Class<T> clazz){
		return (T) JSON.parseObject(str,clazz);
	}
	
	/**
	 * 将Json文本数据信息转换为JsonObject对象，然后利用键值对的方式获取信息
	 * 方法用途: <br>
	 * @param str
	 * @return JsonObject
	 */
	public static  JSONObject StringToJsonObject(String str){
		JSONObject object = JSON.parseObject(str);
		return object;
	}
	
	/**
	 * 将Map类型的数据转换为JsonString
	 * 方法用途: <br>
	 * @param map
	 * @return
	 */
	public static  String MapToJsonString(Map<String,Object> map){
		String jsonString = JSON.toJSONString(map);
		return jsonString;
	}
	
}
