package www.loujie.com.thread;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import www.loujie.com.utils.FfmpegUtils;

public class FfmpegExcutorUtils {

	private static ExecutorService executorService = Executors.newCachedThreadPool();

	public static void encryptionM3u8(List<File> m3u8List) {
		
	}

	public class FfmpegM3u8Encryption implements Runnable {

		private File m3u8File = null;

		public FfmpegM3u8Encryption(File m3u8File) {
			this.m3u8File = m3u8File;
		}

		@Override
		public void run() {
			FfmpegUtils.Ffmpeg.m3u8_cryption(this.m3u8File.getAbsolutePath(), 4);

		}

	}

}
