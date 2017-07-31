package www.loujie.com.task;

import www.loujie.com.main.Main;
import www.loujie.com.utils.FfmpegUtils;

public class FfmpegMain {
	public static void main(String[] args) throws InterruptedException {
		new FfmpegMain().sub_main();
	}

	public void sub_main() throws InterruptedException {
		System.err.println("################################");
		System.err.println("###  A=mp4转换成m3u8           ");
		System.err.println("###  B=对已经生成好的m3u8文件加密       ");
		System.err.println("###  D=定时任务     ");
		System.err.println("###  C=退出                                                    ");
		System.err.println("################################");

		Thread.sleep(1000);
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
				// MP4--m3u8
				mp4_m3u8();
				break;
			case "B" :
				// m3u8-encryption
				m3u8_encryption();
				break;
			case "D" :
			default :
				System.exit(0);
		}
	}

	public void m3u8_encryption() {
		System.out.print("请输入要加密m3u8的全路径:");
		String m3u8File = Main.scanner.nextLine();
		System.out.print("请输入每隔几个值要加密一下的值:");
		Integer modValue = Main.scanner.nextInt();
		FfmpegUtils.Ffmpeg.m3u8_cryption(m3u8File, modValue);
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
