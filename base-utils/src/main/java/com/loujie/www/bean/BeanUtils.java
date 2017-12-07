package com.loujie.www.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 一些对象的操作
 * 
 * @author loujie
 *
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	/**
	 * 将orig中的数据复制到dest中
	 * 
	 * @param dest 目标对象
	 * @param orig 原对象
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void copyBean(Object dest, Object orig) {
		try {
			if (dest instanceof Map) {
				((Map) dest).putAll(describe(orig));
				return;
			}
			copyProperties(dest, orig);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

}
