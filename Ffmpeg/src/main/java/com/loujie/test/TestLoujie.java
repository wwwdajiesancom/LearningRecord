package com.loujie.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.loujie.www.redis.RedisUtils;

import redis.clients.jedis.JedisPubSub;

public class TestLoujie {
	public static void main(String[] args) {
		String[] abcs = {"channel_live", "channel_test"};
		if (args == null || args.length == 0) {
			abcs = new String[]{"channel_live", "channel_test", "channel_update"};
		}
		System.err.println("qidong");
		System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		boolean result = RedisUtils.subscribe(new LiveJedisPubSub(), abcs);
		System.err.println("杰哥");
		System.err.println(result);
	}
}

class LiveJedisPubSub extends JedisPubSub {

	@Override
	public void onMessage(String channel, String message) {
		switch (channel) {
			case "channel_update" :
				this.unsubscribe();
				break;
			default :
				break;
		}
		System.err.println(channel + ":" + message);
	}

}