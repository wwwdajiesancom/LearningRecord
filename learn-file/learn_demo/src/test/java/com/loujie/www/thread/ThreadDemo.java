package com.loujie.www.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.loujie.www.utils.ArgsUtils;

public class ThreadDemo {

	@Test
	public void screetDemo() {
		String uid = "1234";
		String tempCourseId = "nbslise";
		String tempCoursewareId = "fsd232fsd";
		String tempSubmitKey = "njiejiejie";
		String tempRandomStr = "jfsdlkfjsj";
		String str = "uid=" + uid + //
				"&courseId=" + tempCourseId + //
				"&submitKey=" + tempSubmitKey + //
				"&coursewareId=" + tempCoursewareId + //
				"&randomStr=" + tempRandomStr;
		String str2 = "uid=" + uid + //
				"&course_id=" + tempCourseId + //
				"&courseware_id=" + tempCoursewareId + //
				"&submitKey=" + tempSubmitKey + //
				"&randomStr=" + tempRandomStr;
		String sign = ArgsUtils.MD5Utils.md5(str);
		System.err.println("参数:" + str2 + "&sign=" + sign);
		System.err.println("参与加密的字符串:" + str);
		System.err.println("加密后的字符串:" + sign);

	}

	@Test
	public void thr() throws InterruptedException {
		System.out.println(Runtime.getRuntime().availableProcessors());
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

		// Thread.sleep(20000);

	}

	public class Tl implements Runnable {

		private int i;

		public Tl(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println("run" + i);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("runnnnnnnnnnnn" + i);
		}

	}

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
