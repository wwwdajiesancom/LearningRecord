import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.loujie.www.io.file.FileUtils;

public class RenameFile {

	private BufferedWriter bWriter;
	private BufferedReader bReader;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	}

	public void rename(String fileSrc, String fileName) {
		Map<String, String> map = getNameMap(new File(fileSrc, fileName).getAbsolutePath());
		for (Map.Entry<String, String> me : map.entrySet()) {
			renameFile(fileSrc, me.getKey(), me.getValue());
		}
	}

	/**
	 * 重命名
	 * 
	 * @param oldfile
	 * @param newFileName
	 * @return
	 */
	private static boolean renameFile(String fileSrc, String oldfileName, String newFileName) {
		File oldFile = new File(fileSrc, oldfileName);
		if (oldFile.exists() && oldFile.isFile()) {
			oldFile.renameTo(new File(oldFile.getParent(), newFileName));
		}
		return false;
	}

	private String randStr() {
		return UUID.randomUUID().toString();
	}

	private Map<String, String> getNameMap(String filePath) {
		Map<String, String> nameMap = new LinkedHashMap<>();
		try {
			File oldM3u8 = new File(filePath);
			nameMap.put(oldM3u8.getName() + ".swp", oldM3u8.getName() + ".swp.1");
			nameMap.put(oldM3u8.getName(), oldM3u8.getName() + ".swp");
			nameMap.put(oldM3u8.getName() + ".swp.1", oldM3u8.getName());
			bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(oldM3u8))));
			bWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(new File(oldM3u8.getParent(), nameMap.get(oldM3u8.getName()))))));
			String str = null;
			do {
				str = bReader.readLine();
				if (str != null) {
					bWriter.write(str);
					bWriter.newLine();
					if (str.toUpperCase().startsWith("#EXTINF")) {
						str = bReader.readLine();
						if (nameMap.get(str) == null) {
							nameMap.put(str, randStr() + ".ts");
						}
						str = nameMap.get(str);
						bWriter.write(str);
						bWriter.newLine();
					}
				}
			} while (str != null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			FileUtils.close(bWriter, bReader);
		}
		return nameMap;
	}

}
