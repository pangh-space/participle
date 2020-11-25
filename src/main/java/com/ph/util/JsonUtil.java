package com.ph.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description JsonUtil
 * @Author pangh
 * @Date 2020年05月22日 9:52 上午
 * @Version v1.0.0
 */
public class JsonUtil {
	
	
	/**
	 * json字符串转换Map
	 * 
	 * @param json
	 * @return
	 */
	public static <T> Map<String,T> jsonToMap(String json) {

		Map<String,T> map = JSON.parseObject(json,HashMap.class);
		
		return map;
	}

	/**
	 * Json 转换List
	 *
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> jsonToList(String json,Class<T> clazz){
		return JSON.parseArray(json,clazz);

	}
	
}
