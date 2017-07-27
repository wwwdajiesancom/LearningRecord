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
		return new JedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				String result = jedis.set(key, value);
				if ("ok".equalsIgnoreCase(result)) {
					return cla.cast(true);
				}
				return cla.cast(false);
			}
		}.run(Boolean.class);
	}

	/**
	 * 获取key的值
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static String get(final String key) {
		return new JedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				String result = jedis.get(key);
				if (ArgsUtils.isEmpty(result)) {
					return null;
				}
				return cla.cast(result);
			}
		}.run(String.class);
	}

}