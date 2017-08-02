package com.loujie.www.rediscluster;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.loujie.www.properties.PropertiesUtils;
import com.loujie.www.util.ArgsUtils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClusterResource {

	private static final int MaxActive = 64;// 可用连接实例的最大数目，为负值时没有限制。
	private static final int MaxIdle = 4;// 空闲连接实例的最大数目，为负值时没有限制
	private static final int MaxWait = 5000;// 等待可用连接的最大数目，单位毫秒（million seconds）
	private static final boolean TEST_ON_BORROW = true;
	private static final int timeout = 3000;

	// 获取redis集群的相关配置信息
	private static List<String> jedisClusterHosts = //
			ArgsUtils.toList(PropertiesUtils.getProperty("redis_cluster_hosts").split(","));

	private static final JedisCluster jedisCluster;

	static {
		// 1.redis节点
		Set<HostAndPort> jedisClusterNodes = new HashSet<>();
		for (String item : jedisClusterHosts) {
			jedisClusterNodes.add(new HostAndPort(item.split(":")[0], Integer.parseInt(item.split(":")[1])));
		}
		// 2.连接池配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(MaxActive);
		config.setMaxIdle(MaxIdle);
		config.setMaxWaitMillis(MaxWait);
		config.setTestOnBorrow(TEST_ON_BORROW);
		// 3.初始化集群
		jedisCluster = new JedisCluster(jedisClusterNodes, timeout, config);
	}

	/**
	 * 对外提供的唯一方法
	 * 
	 * @return
	 */
	public static JedisCluster getJedisCluster() {
		return jedisCluster;
	}

}
