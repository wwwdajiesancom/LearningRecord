package com.loujie.www.redis;

import redis.clients.jedis.Jedis;

/**
 * 定义一个钩子函数,可以缺省一些步骤
 * 
 * @author loujie
 *
 */
public abstract class JedisCommand {
	private Jedis jedis = null;

	/**
	 * 初始化Jedis参数
	 */
	private void getJedis() {
		jedis = RedisResource.getJedis();
	}

	/**
	 * 关闭Jedis
	 */
	private void close() {
		if (jedis != null) {
			try {
				jedis.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 代码体,最核心的部分
	 * 
	 * @param jedis
	 * @param cla
	 *            要返回值类型
	 * @return
	 */
	abstract <T> T run(Jedis jedis, Class<T> cla);

	/**
	 * 启动,执行代码体的内容
	 * 
	 * @param cla
	 *            返回值内容
	 * @return
	 */
	public <T> T exec(Class<T> cla) {
		// 1.初始化
		this.getJedis();
		try {
			// 2.执行命令
			return run(jedis, cla);
		} finally {
			// 关闭
			this.close();
		}
	}
}
