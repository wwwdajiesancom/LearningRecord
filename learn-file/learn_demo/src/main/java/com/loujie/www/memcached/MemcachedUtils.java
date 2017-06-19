package com.loujie.www.memcached;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedUtils {

	public static class MemcachedClient2 {

	}

	public static void main(String[] args) throws IOException {
		InetSocketAddress ia = new InetSocketAddress(InetAddress.getLocalHost(), 11211);
		MemcachedClient mc = new MemcachedClient(ia);
		
		
		
	
		mc.add("jiege", 9000, "ooooooxxxxxx");

		System.err.println(mc.get("jiege"));

		mc.shutdown();
	}

}
