package www.loujie.com.main;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.loujie.www.properties.PropertiesUtils;
import com.loujie.www.redis.RedisUtils;

import www.loujie.com.task.FfmpegMain;

/**
 * 主入口
 * 
 * @author loujie
 *
 */
public class Main {

	public static final Logger logger = Logger.getLogger(Main.class);

	public static String thread_channel = "thread_channel";
	public static String thread_channel_message = "close";

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException, IOException {
		// args:0=线程数量(数字)/关闭线程池(字母);1=是否开启备份;2=config配置文件的路径
		if (args != null && args.length >= 3) {
			PropertiesUtils.reload(args[2]);
		}
		try {
			// 如果有异常
			Integer.parseInt(args[0]);
			// 加密
			FfmpegMain.main(args);
		} catch (Exception e) {
			// 关闭
			long result = RedisUtils.publish(thread_channel, thread_channel_message);
			if (result > 0) {
				logger.info("关闭了通道");
			} else {
				logger.info("无消费端");
			}
		}
	}

}
