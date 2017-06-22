package com.loujie.www.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

import com.loujie.www.utils.ArgsUtils;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class MemcachedUtils {

	private MemcachedUtils() {
	}

	/**
	 * 查询
	 * 
	 * @author loujie
	 *
	 */
	public static class Query {

		/**
		 * 获取键的值
		 * 
		 * @param key
		 * @param cla
		 *            值的类型
		 * @return
		 */
		public static <T> T get(String key, Class<T> cla) {
			return cla.cast(MemcachedClientUtils.mc.get(key));
		}

		/**
		 * 
		 * @param key
		 * @return
		 */
		public static Object get(String key) {
			return MemcachedClientUtils.mc.get(key);
		}

	}

	/**
	 * 存储
	 * 
	 * @author loujie
	 *
	 */
	public static class Store {

		private static Integer getExp(Integer... expireSeconds) {
			if (expireSeconds == null || expireSeconds.length == 0) {
				expireSeconds = new Integer[]{0};
			}
			return expireSeconds[0];
		}

		private static boolean get(OperationFuture<Boolean> of) {
			if (of == null) {
				return false;
			}
			try {
				return of.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * 设置一对：键-值,如果存在则:替换,不存在:增加
		 * 
		 * @param key
		 *            键
		 * @param value
		 *            值
		 * @param expireSeconds
		 *            时效,0=永不过期
		 * @return
		 */
		public static boolean set(String key, Object value, Integer... expireSeconds) {
			return get(MemcachedClientUtils.mc.set(key, getExp(expireSeconds), value));
		}

		/**
		 * 添加一对：键-值,如果不存在:添加,存在:失败
		 * 
		 * @param key
		 *            键,
		 * @param value
		 *            值
		 * @param expireSeconds
		 *            时效,0=永不过期
		 * @return
		 */
		public static boolean add(String key, Object value, Integer... expireSeconds) {
			return get(MemcachedClientUtils.mc.add(key, getExp(expireSeconds), value));
		}

		/**
		 * 替换值
		 * 
		 * @param key
		 * @param value
		 * @param expireSeconds
		 * @return
		 */
		public static boolean replace(String key, Object value, Integer... expireSeconds) {
			return get(MemcachedClientUtils.mc.replace(key, getExp(expireSeconds), value));
		}

		/**
		 * 删除键
		 * 
		 * @param key
		 *            键
		 * @return
		 */
		public static boolean delete(String key) {
			return get(MemcachedClientUtils.mc.delete(key));
		}

		public static boolean incr(String key, int incrStep) {
			long i = MemcachedClientUtils.mc.incr(key, incrStep);
			System.err.println("i:" + i);
			return false;
		}

	}

	/**
	 * 提供 MemcachedClient
	 * 
	 * @author loujie
	 *
	 */
	private static class MemcachedClientUtils {
		private static MemcachedClient mc = null;
		static {
			String addr = ArgsUtils.PropertisUtils.getProperty("memcached_ip", "127.0.0.1");// TODO memcached的服务器地址
			Integer port = ArgsUtils.parseInteger(ArgsUtils.PropertisUtils.getProperty("memcached_port", "11211"));// TODO memcached的端口
			InetSocketAddress ia = null;
			try {
				ia = new InetSocketAddress(addr, port);
				mc = new MemcachedClient(ia);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
