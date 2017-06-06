package com.loujie.www.designpattern.create.signleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 静态内部类模式(最好)
 * 
 * @see 1.私有构造函数
 * @see 2.私有的静态内部类,里面定义一个私有的静态对象
 * @see 3.对外的静态方法
 * @author loujie
 *
 */
public class InnerClassObject {

	// 2.私有的静态内部类,里面定义一个私有的静态对象
	private static class SubInnerClassObject {
		// 2.1静态的变量
		private static final InnerClassObject instance = new InnerClassObject();
	}

	private Properties properties = new Properties();
	{
		try {
			properties.load(new FileInputStream("C:\\Users\\loujie\\Desktop\\git\\loujitest\\java-ee\\javaee\\src\\main\\resources\\config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 1.私有构造函数
	private InnerClassObject() {
	}

	// 3.对外的方法
	public static InnerClassObject getInstance() {
		return SubInnerClassObject.instance;
	}

	public String getProperty(String key, String... defaults) {
		String defaultV = null;
		if (defaults != null && defaults.length > 0) {
			defaultV = defaults[0];
		}
		return properties.getProperty(key, defaultV);
	}
}
