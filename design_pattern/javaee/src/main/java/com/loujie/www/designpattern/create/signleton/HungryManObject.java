package com.loujie.www.designpattern.create.signleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * 饿汉式单例
 * 
 * @see 线程安全,访问效率高;但不能延时加载
 * @see 1.[私有的构造函数]
 * @see 2.一个[私有的][已经实例化的][静态变量]
 * @see 3.一个[对外的],[获取变量的静态方法]
 * @see 因为它是在jvm加载时初始化的,所以不用使用sync
 * @author loujie
 *
 */
public class HungryManObject implements Serializable {

	private static final long serialVersionUID = 9122806986661958390L;

	// 2.一个[私有的][已经实例化的][静态变量];因为已经实例化了,所以
	private static HungryManObject hmObject = new HungryManObject();

	private Properties properties = new Properties();
	{
		try {
			properties.load(new FileInputStream("C:\\Users\\loujie\\Desktop\\git\\loujitest\\java-ee\\javaee\\src\\main\\resources\\config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 1.[私有的构造函数]
	private HungryManObject() {
	}

	// 3.一个[对外的],[获取变量的静态方法]
	public /* synchronized */ static HungryManObject getInstance() {
		return hmObject;
	}

	public String getProperty(String key, String... defaults) {
		String defaultV = null;
		if (defaults != null && defaults.length > 0) {
			defaultV = defaults[0];
		}
		return properties.getProperty(key, defaultV);
	}

}
