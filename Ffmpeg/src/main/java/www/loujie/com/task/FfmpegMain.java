package www.loujie.com.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.loujie.www.redis.RedisUtils;
import com.loujie.www.serialize.SerializeUtils;

import redis.clients.jedis.JedisPubSub;
import www.loujie.com.entity.DirEntry;
import www.loujie.com.main.Main;
import www.loujie.com.utils.FfmpegUtils;
import www.loujie.com.utils.SyncObject;

public class FfmpegMain {
	// 格式化
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	// 整体的目录结构对象
	private DirEntry srcEntry = null;

	ThreadPoolExecutor threadPool;

	// 记录日志
	public static final Logger logger = Logger.getLogger(FfmpegMain.class);
	// key的更换频率
	private static int modValue = 4;

	// 主函数
	public static void main(String[] args) throws InterruptedException {
		new FfmpegMain().sub_main(args);
	}

	// 子函数
	public void sub_main(String[] args) throws InterruptedException {
		// 1.启动一个线程,读取是否要关闭线程池
		new Thread(new Runnable() {
			JedisPubSub jedisPubSub = new JedisPubSub() {
				@Override
				public void onMessage(String channel, String message) {
					if (Main.thread_channel.equals(channel) && Main.thread_channel_message.equals(message)) {
						if (threadPool != null && !threadPool.isShutdown()) {
							threadPool.shutdown();
						}
						this.unsubscribe();
					}
				}
			};
			@Override
			public void run() {
				RedisUtils.subscribe(Main.thread_channel, jedisPubSub);
				logger.info("关闭订阅:" + Main.thread_channel);
			}
		}).start();

		System.err.println("################################");
		System.err.println("###  B=对已经生成好的m3u8文件加密       ");
		System.err.println("###  C=退出                                                    ");
		System.err.println("################################");
		Thread.sleep(10);
		// 1.输入值
		String type = "C";
		System.out.print("请出入操作类型:");
		type = Main.scanner.nextLine();
		if (type == null || type.isEmpty()) {
			type = "C";
		}

		// 2.根据类型去处理力
		switch (type.toUpperCase()) {
			case "A" :
				// mp4_m3u8();
				break;
			case "B" :
				m3u8_encryption_dir(args);
				break;
			case "D" :
			default :
				System.exit(0);
		}
	}

	public void m3u8_encryption_dir(String[] args) throws InterruptedException {
		// 1.输入参数
		System.out.print("请输入要加密m3u8文件的目录:");
		String m3u8Dir = Main.scanner.nextLine();
		if (!new File(m3u8Dir).exists()) {
			return;
		} ;
		System.out.print("请输入每隔几个值要加密一下的值:");
		modValue = Main.scanner.nextInt();

		// 2.读取缓存
		// TODO 2.1从缓存中读取

		// 2.2自己实例化
		if (srcEntry == null) {
			srcEntry = new DirEntry(m3u8Dir, true);
		}

		// 实例化线程池
		threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(Integer.parseInt(args[0]));

		// 2.3 备份数据
		new Thread(new Runnable() {
			@Override
			public void run() {
				do {
					// 备份
					synchronized (SyncObject.Load_File_Lock) {
						try {
							bak();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					// 等待
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					// 当线程池停止运行了，就结束
				} while (!threadPool.isShutdown());
			}
		}).start();

		long start = Calendar.getInstance().getTimeInMillis();
		// 线程池
		// 3.遍历
		for_dir(srcEntry, null, modValue, threadPool, 1);
		// 4.等待完成;主要是为了让备份完成
		if (!threadPool.isShutdown()) {
			threadPool.shutdown();
			RedisUtils.publish(Main.thread_channel, Main.thread_channel_message);
		}
		do {
			Thread.sleep(100);
		} while (!threadPool.isTerminated());
		// 最后一次备份
		try {
			bak();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("完成加密了,历时:" + (Calendar.getInstance().getTimeInMillis() - start) / 1000 + "s");
	}

	/**
	 * 备份程序
	 * 
	 * @throws IOException
	 */
	public void bak() throws IOException {
		byte[] abc = SerializeUtils.serialize(srcEntry);
		File bakFile = new File(srcEntry.getPath(), DirEntry.bak_file_name);
		File bakFile2 = new File(srcEntry.getPath(), DirEntry.bak_file_name + 1);
		if (bakFile2.exists()) {
			bakFile2.delete();
		}
		if (bakFile.exists()) {
			bakFile.renameTo(bakFile2);
		}
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(bakFile));
		bos.write(abc);
		bos.flush();
		bos.close();
		logger.info("备份成功...............................");
	}

	/**
	 * 遍历加密
	 * 
	 * @param childEntiry
	 *            子
	 * @param dirEntry
	 *            父
	 * @param modValue
	 *            更换频率key的
	 * @param eService
	 *            线程池
	 * @param numberC
	 *            第几层循环
	 */
	public void for_dir(final DirEntry childEntiry, final DirEntry parentEntry, final int modValue, ThreadPoolExecutor threadPool, int numberC) {
		// 1.判断是否加载了,
		// 非叶子节点,并且尚未加载
		if (!childEntiry.getIsLeaf() && childEntiry.getChildDirEntrys().size() == 0) {
			FfmpegUtils.FileUtils.loadDirAndM3u8(childEntiry);
		}
		// 1.1目录下没有东西
		// 1.2目录下的东西已经被处理完成
		if (childEntiry.getCompleteStatus()) {
			logger.info(numberC + ":完成了" + childEntiry.getPath());
			return;
		}

		// 2.遍历目录
		for (DirEntry item : childEntiry.getChildDirEntrys()) {
			for_dir(item, childEntiry, modValue, threadPool, numberC + 1);
		}

		// 3.遍历文件
		for (final Object item : childEntiry.getUnchildFiles().toArray()) {
			addRunnableToThreadPoolExecutor(threadPool, item, childEntiry, parentEntry);
		}
	}

	public void addRunnableToThreadPoolExecutor(ThreadPoolExecutor tpe, final Object item, final DirEntry childEntry, final DirEntry parentEntry) {
		// 1.如果大于CorePoolSize*2就等待1s
		int imax = tpe.getCorePoolSize() << 2;
		do {
			if (tpe.getQueue().size() > imax) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (tpe.getQueue().size() > imax);

		// 2.增加任务
		tpe.execute(new Runnable() {
			@Override
			public void run() {
				try {
					// 1.加密
					String start = sdf.format(Calendar.getInstance().getTime());
					FfmpegUtils.Ffmpeg.m3u8_cryption_redis(item.toString(), modValue);
					String end = sdf.format(Calendar.getInstance().getTime());
					logger.info("start:" + start + ",end:" + end + ",enc success:" + item);
					// 2.完成文件记录
					childEntry.getCompleteChildFiles().add(item.toString());
					childEntry.getUnchildFiles().remove(item);
					// 3.同步参数状态
					// parentEntry.getCompleteStatus();
				} catch (Exception e) {
					logger.error("enc error:" + item);
					childEntry.getFailChildFiles().add(item.toString());
				}
			}
		});
	}

	public void m3u8_encryption() {
		System.out.print("请输入要加密m3u8的全路径:");
		String m3u8File = Main.scanner.nextLine();
		System.out.print("请输入每隔几个值要加密一下的值:");
		Integer modValue = Main.scanner.nextInt();
		FfmpegUtils.Ffmpeg.m3u8_cryption_redis(m3u8File, modValue);
	}

	public void mp4_m3u8() {
		System.out.print("请输入mp4文件的父路径:");
		String mp4Dir = Main.scanner.nextLine();
		System.out.print("请输入mp4文件的名称:");
		String mp4Name = Main.scanner.nextLine();
		System.out.print("请输入要生成m3u8文件的目录:");
		String m3u8Dir = Main.scanner.nextLine();
		System.out.print("请输入要生成m3u8文件的名称:");
		String m3u8Name = Main.scanner.nextLine();
		System.out.print("请输入生成ts文件的前缀:");
		String tsPrefix = Main.scanner.nextLine();
		FfmpegUtils.Ffmpeg.ffmpeg_mp4_to_hls(mp4Dir, mp4Name, m3u8Dir, m3u8Name, tsPrefix);
	}

}
