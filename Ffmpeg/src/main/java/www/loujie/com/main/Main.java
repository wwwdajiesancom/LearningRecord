package www.loujie.com.main;

import java.util.Scanner;

import www.loujie.com.submain.DownloadM3u8Main;

public class Main {

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		subMain();
	}

	public static void subMain() throws InterruptedException {
		// 1.提示
		System.err.println("##########################################################################");
		System.err.println("###A=下载m3u8文件");
		System.err.println("###");
		System.err.println("###");
		System.err.println("###");
		System.err.println("###");
		System.err.println("###输入其它的,退出");
		// System.err.println("###");
		System.err.println("##########################################################################");
		Thread.sleep(100);

		// 2.根据输入的内容
		System.out.print("请输入操作类型:");
		String type = scanner.nextLine();
		if (type == null || type.isEmpty()) {
			subMain();
			return;
		}
		switch (type.toUpperCase()) {
			case "A" :// 下载m3u8文件
				DownloadM3u8Main.download();
				break;
			default :
				System.exit(0);
				return;
		}
		subMain();
	}

}
