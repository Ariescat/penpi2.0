package org.penpi.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonString {

	public static String toJsonString(Object obj) throws JsonProcessingException{
        // 将java对象转换为json字符串
		ObjectMapper mapper = new ObjectMapper(); 
		String jsonStr = mapper.writeValueAsString(obj); 
        
        return jsonStr;
    }
}
