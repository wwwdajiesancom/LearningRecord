package com.loujie.www.redis;

import java.util.Map;

import com.loujie.www.util.ArgsUtils;

import redis.clients.jedis.Jedis;

/**
 * Redis里面的一些操作
 * 
 * @author loujie
 *
 */
public class RedisUtils {

	private static String key(String key) {

		return "base_" + key;
	}

	/**
	 * 设置key-value,有有效期
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param expireSeconds
	 *            有效期,-1=永不失效
	 * @return
	 */
	public static boolean set(final String key, final String value, final int expireSeconds) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				String result = jedis.set(key(key), value);
				if ("ok".equalsIgnoreCase(result)) {
					if (expireSeconds >= 0) {
						jedis.expire(key(key), expireSeconds);
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
		return set(key(key), value, -1);
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
				String result = jedis.get(key(key));
				if (ArgsUtils.isEmpty(result)) {
					return null;
				}
				return cla.cast(result);
			}
		}.run(String.class);
	}

	/**
	 * 设置hash键-字段-值到redis中
	 * 
	 * @param key
	 *            键
	 * @param field
	 *            字段
	 * @param value
	 *            值
	 * @param expireSeconds
	 *            有效期,-1=永不失效
	 * @return
	 */
	public static boolean hset(final String key, final String field, final String value, final int expireSeconds) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				long result = jedis.hset(key(key), field, value);
				if (result > 0) {
					if (expireSeconds > 0) {
						jedis.expire(key(key), expireSeconds);
					}
					return cla.cast(true);
				}
				return cla.cast(false);
			}
		}.run(Boolean.class);
	}

	/**
	 * 设置hash的键-字段-值到redis
	 * 
	 * @param key
	 *            键
	 * @param field
	 *            字段
	 * @param value
	 *            值
	 * @return
	 */
	public static boolean hset(final String key, final String field, final String value) {
		return hset(key(key), field, value, -1);
	}

	/**
	 * 获取hash的键-字段的值
	 * 
	 * @param key
	 *            键
	 * @param field
	 *            字段
	 * @return
	 */
	public static String hget(final String key, final String field) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				return cla.cast(jedis.hget(key(key), field));
			}
		}.run(String.class);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> hget(final String key) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				return (T) jedis.hgetAll(key(key));
			}
		}.run(Map.class);
	}

}