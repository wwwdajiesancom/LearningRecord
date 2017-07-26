package com.loujie.www.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 序列化,与反序列化
 * 
 * @author loujie
 *
 */
public class SerializeUtils {

	private static final String TEMP_ENCODING = "ISO-8859-1";
	private static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static String serializeToString(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			String result = baos.toString(TEMP_ENCODING);
			return URLEncoder.encode(result, DEFAULT_ENCODING);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 反序列化
	 * 
	 * @param <T>
	 * 
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unserialize(byte[] bytes, Class<T> cla) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 反序列化
	 * 
	 * @param <T>
	 * 
	 * @param serialize
	 * @return
	 */
	public static <T> T unserialize(String serialize, Class<T> cla) {
		if (serialize == null || serialize.isEmpty()) {
			return null;
		}
		try {
			serialize = URLDecoder.decode(serialize, DEFAULT_ENCODING);
			return unserialize(serialize.getBytes(TEMP_ENCODING), cla);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
