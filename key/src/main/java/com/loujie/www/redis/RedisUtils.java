package com.loujie.www.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

		return key;
	}

	@SuppressWarnings("unchecked")
	public static List<String> getM3u8KeyPrefix(final int keyPrefix) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				Set<String> keys = jedis.keys(keyPrefix + "-*");
				if (keys != null && keys.size() == 1) {
					String key = keys.toArray()[0].toString();
					String result = jedis.get(key);
					if (result != null && !result.isEmpty()) {
						List<String> resultList = new ArrayList<>();
						resultList.add(key.split("-")[1]);
						resultList.add(result);
						return cla.cast(resultList);
					}
				}
				return null;
			}
		}.run(List.class);
	}

	/**
	 * db的键的数量
	 * 
	 * @return
	 */
	public static Long keySize() {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				return cla.cast(jedis.dbSize());
			}
		}.run(Long.class);
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

	/**
	 * 获取值,模糊匹配
	 * 
	 * @param keySuffixPattern
	 * @return
	 */
	public static String getM3u8KeyValue(final String keySuffixPattern) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				Set<String> keys = jedis.keys("*-" + key(keySuffixPattern));
				if (keys != null && keys.size() == 1) {
					String result = jedis.get(keys.toArray()[0].toString());
					if (result != null && !result.isEmpty()) {
						return cla.cast(result);
					}
				}
				return null;
			}
		}.run(String.class);
	}

}