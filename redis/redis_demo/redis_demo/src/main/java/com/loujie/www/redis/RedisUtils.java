package com.loujie.www.redis;

import com.loujie.www.utils.ArgsUitls;

import redis.clients.jedis.JedisPool;

public class RedisUtils {

	public static boolean set(String key, String value) {
		String result = Jedis.getJedis().set(key, value);
		if ("ok".equalsIgnoreCase(result)) {
			return true;
		}
		return false;
	}
	
	

}

/**
 * 获取Jedis
 * 
 * @author loujie
 *
 */
class Jedis {
	private static final String redisIp = ArgsUitls.Config.get("redis_ip");
	private static final Integer redisPort = Integer.parseInt(ArgsUitls.Config.get("redis_port"));

	static JedisPool jedisPool;
	static {
		jedisPool = new redis.clients.jedis.JedisPool(redisIp, redisPort);
	}

	public static redis.clients.jedis.Jedis getJedis() {
		return jedisPool.getResource();
	}
}
