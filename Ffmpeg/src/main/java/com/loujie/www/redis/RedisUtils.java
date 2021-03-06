package com.loujie.www.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Redis里面的一些操作
 * 
 * @author loujie
 *
 */
public class RedisUtils {

	/**
	 * 删除键
	 * 
	 * @param keys
	 * @return
	 */
	public static boolean delKey(final String... keys) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				Long result = jedis.del(keys);
				if (result == null || (result - 0 == 0)) {
					return cla.cast(false);
				}
				return cla.cast(true);
			}
		}.run(Boolean.class);
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
				String result = jedis.set((key), value);
				if ("ok".equalsIgnoreCase(result)) {
					if (expireSeconds >= 0) {
						jedis.expire((key), expireSeconds);
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
		return set((key), value, -1);
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
				String result = jedis.get((key));
				if (result == null || result.isEmpty()) {
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
				long result = jedis.hset((key), field, value);
				if (result > 0) {
					if (expireSeconds > 0) {
						jedis.expire((key), expireSeconds);
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
		return hset((key), field, value, -1);
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
				return cla.cast(jedis.hget((key), field));
			}
		}.run(String.class);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> hget(final String key) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				return (T) jedis.hgetAll((key));
			}
		}.run(Map.class);
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
	 * 订阅
	 * 
	 * @param jedisPubSub
	 *            怎么处理
	 * @param channels
	 *            订阅的频道
	 * @return
	 */
	public static Boolean subscribe(final JedisPubSub jedisPubSub, final String... channels) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				jedis.subscribe(jedisPubSub, channels);
				return cla.cast(true);
			}
		}.run(Boolean.class);
	}

	/**
	 * 发布
	 * 
	 * @param channel
	 *            频道
	 * @param message
	 *            发布信息
	 * @return
	 */
	public static long publish(final String channel, final String message) {
		return new RedisCallback() {
			@Override
			<T> T callback(Jedis jedis, Class<T> cla) {
				long result = jedis.publish(channel, message);
				return cla.cast(result);
			}
		}.run(Long.class);
	}

}