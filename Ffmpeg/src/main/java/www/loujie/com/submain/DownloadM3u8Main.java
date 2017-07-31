package www.loujie.com.submain;

import java.util.UUID;

import www.loujie.com.main.Main;
import www.loujie.com.utils.FfmpegUtils;
import www.loujie.com.utils.FileDownloadUtils;

/**
 * 下载文件
 * 
 * @author loujie
 *
 */
public class DownloadM3u8Main {

	public static void main(String[] args) {
		FileDownloadUtils.downNetworkResource("http://liuxue.cloudp.cc/jiaoyu/data/video/hls/201610/eed7c1f07338e511/LD/20161024-eed7LD.m3u8", UUID.randomUUID().toString(), "D:\\t");
	}
	
	public static void download() {
		System.out.print("请输入要下载文件的地址(多个,隔开):");
		String file_urls = Main.scanner.nextLine();
		System.out.print("请输入指定下载目录:");
		String outdir = Main.scanner.nextLine();
		// 1.处理地址
		String[] fileUrls = file_urls.split(",");
		// 2.循环下载
		for (String url : fileUrls) {
			try {
				FileDownloadUtils.downNetworkResource(url, UUID.randomUUID().toString(), outdir);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
