package www.loujie.com.task;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 做为一个定时的扫描器
 * 
 * @author loujie
 *
 */
public class ScanTask {
	private static final Map<String, String> addMap = new Hashtable<>();
	private static final Map<String, String> deleteMap = new Hashtable<>();
	private static Scanner scanner;

	private static volatile Boolean isNext = true;

	public static void main(String[] args) {
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
						if (addMap.isEmpty() && deleteMap.isEmpty()) {
							File file = new File(pathname);
							if (file.exists()) {
								List<File> childFiles = childDirList(file);
								if (childFiles.size() > 0) {
									for (File itemFile : childFiles) {
										System.err.println(itemFile.getAbsolutePath());
									}
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
		// 2.定时
		Timer timer = new Timer();
		timer.schedule(tt, 2000, 1000);
	}

	/**
	 * 获取父目录下的直接子目录
	 * 
	 * @param parentDirFile
	 *            父目录
	 * @return
	 */
	private static List<File> childDirList(File parentDirFile) {
		List<File> returnList = new ArrayList<>();
		if (parentDirFile.exists() && parentDirFile.isDirectory()) {
			File[] files = parentDirFile.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					if (pathname.isDirectory()) {
						return true;
					}
					return false;
				}
			});
			if (files != null && files.length > 0) {
				for (File itemFile : files) {
					returnList.add(itemFile);
				}
			}
		}
		return returnList;
	}

}
