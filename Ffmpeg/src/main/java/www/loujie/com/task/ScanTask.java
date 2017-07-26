package www.loujie.com.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import www.loujie.com.utils.FfmpegUtils;

/**
 * 做为一个定时的扫描器
 * 
 * @author loujie
 *
 */
public class ScanTask {
	private static final List<File> addList = new ArrayList<>();
	private static final List<File> deleteList = new ArrayList<>();
	private static Scanner scanner;

	private static volatile Boolean isNext = true;

	public static void main(String[] args) {
		// 0.设置扫描目录
		scanner = new Scanner(System.in);
		System.out.print("请输入要扫描的目录:");
		final String pathname = scanner.nextLine();
		// 1.定义一个任务
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				try {
					if (isNext) {
						isNext = false;
						if (addList.isEmpty() && deleteList.isEmpty()) {
							File file = new File(pathname);
							if (file.exists()) {
								// 获取所有的子项
								List<File> childFiles = FfmpegUtils.FileUtils.childDirList(file);
								if (childFiles.size() > 0) {
									for (File itemFile : childFiles) {
										// 找出m3u8文件
										List<File> m3u8List = FfmpegUtils.FileUtils.iterationFileBySuffix(itemFile, ".m3u8");
										addList.addAll(m3u8List);
									}
									// 处理加密
								}
							} else {
								System.err.println("目录" + pathname + "不存在.");
							}
						}
						isNext = true;
						return;
					}
				} catch (Exception e) {
					isNext = true;
				}
			}
		};
		// 2.定时,每隔1s扫描一下
		Timer timer = new Timer();
		timer.schedule(tt, 2000, 1000);
	}

}
