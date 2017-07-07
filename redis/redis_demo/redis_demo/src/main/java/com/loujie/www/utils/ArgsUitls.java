package com.loujie.www.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

public class ArgsUitls {

	public static class Config {

		private static Properties pro;
		static {
			pro = new Properties();
			try {
				pro.load(new BufferedInputStream(Config.class.getResource("/config.properties").openStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 获取配置文件中数据
		 * 
		 * @param key
		 *            键
		 * @param defaultValues
		 *            默认值
		 * @return
		 */
		public static String get(String key, String... defaultValues) {
			if (defaultValues == null || defaultValues.length == 0) {
				defaultValues = new String[]{null};
			}
			return pro.getProperty(key, defaultValues[0]);
		}

	}

}
