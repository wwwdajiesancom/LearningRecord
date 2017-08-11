package www.loujie.com.main;

import java.io.IOException;
import java.util.Scanner;

import com.loujie.www.redis.RedisUtils;

import www.loujie.com.task.FfmpegMain;

/**
 * 主入口
 * 
 * @author loujie
 *
 */
public class Main {

	public static String thread_channel = "thread_channel";
	public static String thread_channel_message = "close";

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException, IOException {
		try {
			// 如果有异常
			Integer.parseInt(args[0]);
			// 加密
			FfmpegMain.main(args);
		} catch (Exception e) {
			// 关闭
			RedisUtils.publish(thread_channel, thread_channel_message);
		}
	}

}
