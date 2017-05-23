package com.spider.webmagic.distributd.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 实体转json
	public static <T> JSONObject toJSONObject(T t) {
		JSONObject object = new JSONObject();
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			try {
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + fieldName.substring(1);
				Method method = t.getClass().getMethod(getter, new Class[] {});
				Object value = method.invoke(t, new Object[] {});
				if(value==null){
					object.put(fieldName, "");
				}else{
					object.put(fieldName, value);
				}				
			} catch (Exception e) {
				object.put(fieldName, "");
			}
		}
		return object;
	}

	
}
