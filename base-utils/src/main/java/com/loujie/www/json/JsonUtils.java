package com.loujie.www.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json字符串的操作
 * 
 * @author loujie
 *
 */
public class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		// objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES,
		// false);
		// objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	}

	/**
	 * Object对象转json 2015年4月3日上午10:41:53
	 * 
	 * @param pojo
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static String toJson(Object pojo) {
		try {
			if (pojo == null) {
				return null;
			}
			return objectMapper.writeValueAsString(pojo);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * json字符串转Map 2015年4月3日上午10:41:25
	 * 
	 * @param jsonStr
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseMap(String jsonStr) {
		if (jsonStr == null || jsonStr.isEmpty()) {
			return null;
		}
		Map<String, Object> map = null;
		try {
			map = objectMapper.readValue(jsonStr, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 泛型返回，json字符串转对象 2015年4月3日上午10:42:19
	 * 
	 * @param jsonAsString
	 * @param pojoClass
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public static <T> T parseObject(String jsonAsString, Class<T> pojoClass) {
		try {

			return objectMapper.readValue(jsonAsString, pojoClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> parseList(String jsonAsString, Class<T> cla) {
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, cla);
		try {
			return objectMapper.readValue(jsonAsString, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
