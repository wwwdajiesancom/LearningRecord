package com.loujie.www.test;

import org.junit.Test;

import com.loujie.www.utils.ArgsUitls;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class ReidsDemo {

	@Test
	public void ping() {
		Jedis jedis = new Jedis(ArgsUitls.Config.get("redis_ip"), Integer.parseInt(ArgsUitls.Config.get("redis_port")));
		JedisPubSub jps = new JedisPubSub() {
			public void onMessage(String channel, String message) {
				System.err.println(channel);
				System.err.println("1:" + message);
			}
		};
		jedis.subscribe(jps, "c1");
	}

	@Test
	public void ping2() {
		Jedis jedis = new Jedis(ArgsUitls.Config.get("redis_ip"), Integer.parseInt(ArgsUitls.Config.get("redis_port")));
		JedisPubSub jps = new JedisPubSub() {
			public void onMessage(String channel, String message) {
				System.err.println(channel);
				System.err.println("2:" + message);
			}
		};
		jedis.subscribe(jps, "c1");
	}
	
	@Test
	public void ping3() {
		Jedis jedis = new Jedis(ArgsUitls.Config.get("redis_ip"), Integer.parseInt(ArgsUitls.Config.get("redis_port")));
		JedisPubSub jps = new JedisPubSub() {
			public void onMessage(String channel, String message) {
				System.err.println(channel);
				System.err.println("2:" + message);
			}
		};
		jedis.subscribe(jps, "c1");
	}

}
