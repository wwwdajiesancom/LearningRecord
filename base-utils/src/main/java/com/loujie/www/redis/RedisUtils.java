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

	public static boolean set(final String key, final String value, final int expireSeconds) {

		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				String result = jedis.set(key, value);
				if ("ok".equalsIgnoreCase(result)) {
					if (expireSeconds >= 0) {
						jedis.expire(key, expireSeconds);
					}
					return cla.cast(true);
				}
				return cla.cast(false);
			}
		}.run(Boolean.class);

	}

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
		return set(key, value, -1);
	}

	/**
	 * 获取key的值
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static String get(final String key) {
		return new RedisCallback() {
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

	/**
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @param expireSeconds
	 * @return
	 */
	public static boolean hset(final String key, final String field, final String value, final int expireSeconds) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				long result = jedis.hset(key, field, value);
				if (result > 0) {
					if (expireSeconds > 0) {
						jedis.expire(key, expireSeconds);
					}
					return cla.cast(true);
				}
				return cla.cast(false);
			}
		}.run(Boolean.class);
	}

	/**
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public static boolean hset(final String key, final String field, final String value) {
		return hset(key, field, value, -1);
	}

}