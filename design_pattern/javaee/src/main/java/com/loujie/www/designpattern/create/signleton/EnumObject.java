package com.loujie.www.designpattern.create.signleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public enum EnumObject {

	// 天然单例
	INSTANCE;

	private EnumObject() {
	}

	private Properties properties = new Properties();
	{
		try {
			properties.load(new FileInputStream("C:\\Users\\loujie\\Desktop\\git\\loujitest\\java-ee\\javaee\\src\\main\\resources\\config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key, String... defaults) {
		String defaultV = null;
		if (defaults != null && defaults.length > 0) {
			defaultV = defaults[0];
		}
		return properties.getProperty(key, defaultV);
	}

}
