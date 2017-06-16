package com.loujie.www.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class ThreadDemo {

	@Test
	public void threadDemo() throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(10);
		MyCall task = new MyCall();
		Future<String> lFuture = es.submit(task);
		System.err.println(lFuture.get());
		es.shutdown();
	}

	class MyCall implements Callable<String> {
		@Override
		public String call() throws Exception {

			return "abc";
		}
	}

}
