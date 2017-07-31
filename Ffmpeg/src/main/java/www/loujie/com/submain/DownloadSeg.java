package www.loujie.com.submain;

import java.io.File;
import java.util.List;

import www.loujie.com.main.Main;
import www.loujie.com.utils.FfmpegUtils;

public class DownloadSeg {

	public static void download(String[] args) {
		System.out.print("请输入要下载m3u8文件的目录:");
		String m3u8Dir = Main.scanner.nextLine();
		System.out.print("请输入输出目录:");
		String outM3u8Dir = Main.scanner.nextLine();
		// 1.找到m3u8文件
		List<File> m3u8List = FfmpegUtils.FileUtils.childFileBySuffix(new File(m3u8Dir), ".m3u8");
		// 2.循环下载
		for (File file : m3u8List) {
			// String m3u8Name = ;
			// 2.1创建输出目录

			// 2.2复制m3u8文件

			// 2.3找到ts文件列表

			// 2.4下载相关ts文件
		}

	}

}
