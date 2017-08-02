package com.loujie.www.rediscluster;

import redis.clients.jedis.JedisCluster;

public class RedisClusterUtils {

	// 获取redis连接
	private static final JedisCluster jedisCluster = RedisClusterResource.getJedisCluster();

	/**
	 * 设置key-value到redis中
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static boolean set(String key, String value) {
		String result = jedisCluster.set(key, value);
		System.err.println(result);
		return true;
	}

	/**
	 * 获取redis中键的值
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static String get(String key) {
		return jedisCluster.get(key);
	}
}
