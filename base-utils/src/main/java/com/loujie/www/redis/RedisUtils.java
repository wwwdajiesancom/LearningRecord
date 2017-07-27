package com.loujie.www.redis;

import com.loujie.www.util.ArgsUtils;

import redis.clients.jedis.Jedis;

/**
 * Redis里面的一些操作
 * 
 * @author loujie
 *
 */
public class RedisUtils {

	/**
	 * 设置key-value到redis中
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static boolean set(final String key, final String value) {
<<<<<<< HEAD
		return new JedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
=======
		return new JedisCommand() {
			@Override
			<T> T run(Jedis jedis, Class<T> cla) {
>>>>>>> 4a906f9cb53f27b3e7a713782f22bb342e7dfe0e
				String result = jedis.set(key, value);
				if ("ok".equalsIgnoreCase(result)) {
					return cla.cast(true);
				}
				return cla.cast(false);
			}
<<<<<<< HEAD
		}.run(Boolean.class);
=======
		}.exec(Boolean.class);
>>>>>>> 4a906f9cb53f27b3e7a713782f22bb342e7dfe0e

	}

	/**
	 * 获取key的值
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static String get(final String key) {
<<<<<<< HEAD
		return new JedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
=======
		return new JedisCommand() {
			@Override
			<T> T run(Jedis jedis, Class<T> cla) {
>>>>>>> 4a906f9cb53f27b3e7a713782f22bb342e7dfe0e
				String result = jedis.get(key);
				if (ArgsUtils.isEmpty(result)) {
					return null;
				}
				return cla.cast(result);
			}
<<<<<<< HEAD
		}.run(String.class);
=======
		}.exec(String.class);
>>>>>>> 4a906f9cb53f27b3e7a713782f22bb342e7dfe0e
	}

}