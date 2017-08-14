package www.loujie.com.main;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loujie.www.serialize.SerializeUtils;

import www.loujie.com.entity.DirEntry;

public class MainSub {

	static Logger logger = LoggerFactory.getLogger(MainSub.class);

	private static BufferedInputStream bis;

	public static void main(String[] args) throws IOException {
		bis = new BufferedInputStream(new FileInputStream(new File("D:\\t\\dir_entry.bak")));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = -1;
		do {
			len = bis.read(b);
			if (len != -1) {
				baos.write(b, 0, len);
			}
		} while (len != -1);
		DirEntry dirEntry = SerializeUtils.unserialize(baos.toByteArray(), DirEntry.class);
		bis.close();
		baos.close();
		System.err.println("000xxx");
		// 2.输出内容
		println_dir(dirEntry, 1);
	}

	public static void println_dir(DirEntry dirEntry, int numberC) {
		logger.debug(numberC + ":DirEntry.path:" + dirEntry.getPath() + ",");
		logger.info(numberC + ":DirEntry.completeDirSize:" + dirEntry.getCompleteDirSize());
		logger.info(numberC + ":DirEntry.unchildFiles:" + Arrays.toString(dirEntry.getUnchildFiles().toArray()));
		logger.info(numberC + ":DirEntry.completeChildFiles:" + Arrays.toString(dirEntry.getCompleteChildFiles().toArray()));
		logger.info(numberC + ":DirEntry.failChildFiles:" + Arrays.toString(dirEntry.getFailChildFiles().toArray()));
		logger.info(numberC + ":DirEntry.isRoot:" + dirEntry.getIsRoot());
		logger.info(numberC + ":DirEntry.isLeaf:" + dirEntry.getIsLeaf());
		logger.info(numberC + ":DirEntry.completeStatus:" + dirEntry.getCompleteStatus());
		logger.info("---------------------------------------------------------------------------------------------");
		if (numberC == 1)
			for (DirEntry item : dirEntry.getChildDirEntrys()) {
				println_dir(item, numberC + 1);
			}
	}

}
