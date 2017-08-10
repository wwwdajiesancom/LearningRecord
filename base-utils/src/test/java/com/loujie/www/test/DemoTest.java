package com.loujie.www.test;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.Test;

import com.loujie.www.redis.RedisUtils;
import com.loujie.www.rediscluster.RedisClusterUtils;
import com.loujie.www.util.ConvertUtils;

public class DemoTest {

	@Test
	public void regex() throws Exception {
		// 监控目录
		String rootDir = "d:\\Temp";
		// 轮询间隔 5 秒
		long interval = TimeUnit.SECONDS.toMillis(5);
		//
		FileAlterationObserver observer = new FileAlterationObserver(rootDir, FileFilterUtils.and(FileFilterUtils.fileFileFilter(), FileFilterUtils.suffixFileFilter(".java")), null);
		// observer.addListener(new MyFileListener());
		FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
		// 开始监控
		monitor.start();
	}

	@Test
	public void redis() {
		RedisUtils.set("jiejiejiejie", "娄杰大哥");
		System.out.println(RedisUtils.get("jiejiejiejie"));
	}

	@Test
	public void redisCluster() {
		RedisClusterUtils.set("nameds", "jiejiejei");
		System.out.println(RedisClusterUtils.get("nameds"));
	}

	@Test
	public void convert() {
		byte[] key = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(key);
		for (byte item : key) {
			System.err.println(item);
		}
		String hexStr = ConvertUtils.byte2HexStr(key);
		System.err.println(hexStr);

		byte[] li = ConvertUtils.hexStringToBytes(hexStr);
		System.err.println(li.length);

		for (byte item : li) {
			System.err.println(item);
		}
	}

}
