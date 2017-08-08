package com.loujie.www.redis;

import com.loujie.www.properties.PropertiesUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 从redis池中获取Jedis对象
 * 
 * @author loujie
 *
 */
public class RedisResource {

	private static final int MaxActive = 64;// 可用连接实例的最大数目，为负值时没有限制。
	private static final int MaxIdle = 4;// 空闲连接实例的最大数目，为负值时没有限制
	private static final int MaxWait = 5000;// 等待可用连接的最大数目，单位毫秒（million seconds）
	private static final boolean TEST_ON_BORROW = true;
	// 从配置文件中读取
	private static final String redisIp = PropertiesUtils.getProperty("redis_ip");
	private static final Integer redisPort = Integer.parseInt(PropertiesUtils.getProperty("redis_port"));

	static JedisPool jedisPool;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(MaxActive);
		config.setMaxIdle(MaxIdle);
		config.setMaxWaitMillis(MaxWait);
		config.setTestOnBorrow(TEST_ON_BORROW);
		jedisPool = new JedisPool(config, redisIp, redisPort);
	}

	/**
	 * 获取Jedis对象
	 * 
	 * @return
	 */
	public static Jedis getJedis() {
		return jedisPool.getResource();
	}

}
