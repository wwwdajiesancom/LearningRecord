package www.loujie.com.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loujie.www.properties.PropertiesUtils;
import com.loujie.www.redis.RedisUtils;

import www.loujie.com.task.FfmpegMain;
import www.loujie.com.utils.FfmpegUtils;

/**
 * 主入口
 * 
 * @author loujie
 *
 */
public class Main {

	public static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static String thread_channel = "thread_channel";
	public static String thread_channel_message = "close";

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException, IOException {
		// args:0=线程数量(数字)/关闭线程池(字母=close)/修复(repair);1=是否开启备份;2=config配置文件的路径
		if (args != null && args.length >= 3) {
			PropertiesUtils.reload(args[2]);
		}
		try {
			// 如果有异常
			Integer.parseInt(args[0]);
			// 加密
			FfmpegMain.main(args);
		} catch (Exception e) {
			switch (args[0]) {
				case "repair" :// 修复
					System.out.print("请输入要修复m3u8的全路径:");
					String m3u8Path = scanner.nextLine();
					File m3u8File = new File(m3u8Path);
					if (!m3u8File.exists() || !m3u8File.isFile() || !m3u8File.getName().toLowerCase().endsWith(".m3u8")) {
						logger.warn("输入的信息不正确....");
						System.exit(-1);
					}
					logger.info("开始修复......" + m3u8File.getAbsolutePath());
					FfmpegUtils.Ffmpeg.m3u8_cryption_repair(m3u8File);
					logger.info("修复完成......" + m3u8File.getAbsolutePath());
					break;
				default : // 关闭
					long result = RedisUtils.publish(thread_channel, thread_channel_message);
					if (result > 0) {
						logger.info("关闭了通道");
					} else {
						logger.info("无消费端");
					}
					break;
			}
		}
	}

}
