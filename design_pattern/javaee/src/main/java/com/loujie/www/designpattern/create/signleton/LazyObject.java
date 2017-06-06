package com.loujie.www.designpattern.create.signleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 2.懒汉式单例
 * 
 * @see 1.私有的构造函数
 * @see 2.一个[私有的][静态变量],并且[没有实例化]
 * @see 3.[对外]提供一个[唯一的][静态方法][获取这个对象],需要是[线程安全的]方法
 * 
 * @author loujie
 *
 */
public class LazyObject {

	// 2.一个[私有的][静态变量],并且[没有实例化]
	private static LazyObject lObject = null;

	private Properties properties = new Properties();
	{
		try {
			properties.load(new FileInputStream("C:\\Users\\loujie\\Desktop\\git\\loujitest\\java-ee\\javaee\\src\\main\\resources\\config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 1.私有的构造函数
	private LazyObject() {
	}

	// 3.[对外]提供一个[唯一的][静态方法][获取这个对象],需要是[线程安全的]方法
	public synchronized static LazyObject getInstance() {
		if (lObject == null) {
			lObject = new LazyObject();
		}
		return lObject;
	}

	public String getProperty(String key, String... defaults) {
		String defaultV = null;
		if (defaults != null && defaults.length > 0) {
			defaultV = defaults[0];
		}
		return properties.getProperty(key, defaultV);
	}

}
