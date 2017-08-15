package www.loujie.com.main;

import java.io.File;

public class fTest3 {

	public static void main(String[] args) {
		File m3u8File = new File("D:\\t\\t.zip");
		System.err.println(m3u8File.exists());
		System.err.println(m3u8File.isFile());
		System.err.println(m3u8File.getName());
		if (!m3u8File.exists() || !m3u8File.isFile() || !m3u8File.getName().endsWith(".zip")) {
			System.exit(-1);
		}
	}

}
