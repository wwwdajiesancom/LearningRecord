package www.loujie.com.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import com.loujie.www.properties.PropertiesUtils;
import com.loujie.www.redis.RedisUtils;

import www.loujie.com.entity.DirEntry;
import www.loujie.com.task.FfmpegMain;

public class FfmpegUtils {

	/**
	 * 对文件的操作
	 * 
	 * @author loujie
	 *
	 */
	public static class FileUtils {

		/**
		 * 遍历直接目录
		 * 
		 * @param dirEntry
		 */
		public static void loadDirAndM3u8(DirEntry dirEntry) {
			// 1.目录
			File srcFile = new File(dirEntry.getPath());
			if (srcFile.exists() && srcFile.isDirectory()) {
				// 2.找到其它的目录
				File[] dirFiles = srcFile.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						if (pathname.isDirectory()) {
							return true;
						}
						return false;
					}
				});
				// 3.找到m3u8文件
				File[] files = srcFile.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						if (pathname.isFile() && pathname.getName().toLowerCase().endsWith(DirEntry.file_suffix.toLowerCase())) {
							return true;
						}
						return false;
					}
				});
				synchronized (SyncObject.Load_File_Lock) {
					// 是否为叶子节点
					if (dirFiles.length == 0) {
						dirEntry.setIsLeaf(true);
					}
					for (File dir : dirFiles) {
						DirEntry itemDir = new DirEntry(dir.getAbsolutePath());
						dirEntry.getChildDirEntrys().add(itemDir);
					}
					for (File file : files) {
						dirEntry.getUnchildFiles().add(file.getAbsolutePath());
					}
				}
			}
		}

		/**
		 * 获取目录下,包括所有的子目录中的文件,指定后缀的
		 * 
		 * @param dirFile
		 * @param suffix
		 * @return
		 */
		public static List<File> iterationFileBySuffix(File dirFile, final String suffix) {
			List<File> returnList = new ArrayList<>();
			// 1.直接找suffix文件
			List<File> itemSuffixList = childFileBySuffix(dirFile, suffix);
			returnList.addAll(itemSuffixList);
			// 2.找到子目录,
			List<File> itemDirList = childDirList(dirFile);
			// 3.有子目录,循环;没有子目录,直接的结束
			if (itemDirList != null && itemDirList.size() > 0) {
				for (File itemDir : itemDirList) {
					List<File> itemDirSuffixList = iterationFileBySuffix(itemDir, suffix);
					returnList.addAll(itemDirSuffixList);
				}
			}
			// 4.返回结果
			return returnList;
		}

		/**
		 * 获取目录中的[直接文件]，指定后缀的
		 * 
		 * @param dirFile
		 * @param suffix
		 * @return
		 */
		public static List<File> childFileBySuffix(File dirFile, final String suffix) {
			List<File> returnList = new ArrayList<>();
			if (dirFile.exists() && dirFile.isDirectory()) {
				File[] files = dirFile.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						if (pathname.isFile() && pathname.getName().toLowerCase().endsWith(suffix.toLowerCase())) {
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

		/**
		 * 获取父目录下的[直接子目录]
		 * 
		 * @param parentDirFile
		 *            父目录
		 * @return
		 */
		public static List<File> childDirList(File parentDirFile) {
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

	/**
	 * 对配置文件的操作
	 * 
	 * @author loujie
	 *
	 */
	public static class ReadProperties {
		static final Properties config = new Properties();
		static {
			try {
				config.load(ReadProperties.class.getResourceAsStream("/config.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static String getProperty(String key, String defaultValue) {
			return config.getProperty(key, defaultValue);
		}

		/**
		 * 获取key的value
		 * 
		 * @param key
		 * @return
		 */
		public static String getProperty(String key) {
			return getProperty(key, null);
		}

		/**
		 * 设置key的value
		 * 
		 * @param key
		 * @param value
		 * @return
		 */
		public static void setProperty(String key, String value) {
			config.setProperty(key, value);
		}

	}

	/**
	 * ffmpeg命令执行
	 * 
	 * @author loujie
	 *
	 */
	public static class Ffmpeg {

		public static void m3u8_cryption_repair(File m3u8File) {
			// 1.找到所有的ts
			List<String> tsList = ShellCommon.grep_i_pattern_file_list(".ts", m3u8File.getAbsolutePath());
			// 1.1找到所有加密过的ts
			List<String> aesList = ShellCommon.grep_i_pattern_file_list("#EXT-X-KEY:METHOD=AES-128,URI", m3u8File.getAbsolutePath());
			// 2.跳过aesList的个数
			int t_int = aesList.size();
			List<String> keyList = new ArrayList<>();
			for (int i = t_int; i < tsList.size(); i++) {
				// 3.1ts在哪一行
				// grep -in ts file|awk -F : '{print $1}'
				int number = ShellCommon.grep_in_word_file_number(tsList.get(i), m3u8File.getAbsolutePath());
				// 3.2生成key文件
				if ((i) % FfmpegMain.modValue == 0) {
					// keypath = ShellCommon.openssl_rand_16(m3u8.getParent());
					keyList = RedisUtils.getM3u8KeyPrefix(new Random().nextInt(50000));
				}
				// 3.3加密
				String iv = UUID.randomUUID().toString().replaceAll("-", "");
				openssl_aes128_for_ts(m3u8File.getParent(), tsList.get(i), iv, keyList.get(1));
				// 3.4 插入信息在某一行
				// n是哪一行,i是参数不需要动,....是要插入的内容
				// sed 'ni....' m3u8
				ShellCommon.sed_insert_info(m3u8File.getAbsolutePath(), number - 1, "#EXT-X-KEY:METHOD=AES-128,URI=\"" + PropertiesUtils.getProperty("ffmpeg_uri") + keyList.get(0) + "\",IV=0x" + iv);
			}
		}

		public static void m3u8_cryption_redis(String m3u8File) {
			// 1.判断是否存在
			File m3u8 = new File(m3u8File);
			// 2.判断是否曾经加密过
			// grep -i #EXT-X-KEY:METHOD=AES-128,URI files
			Integer count = ShellCommon.grep_i_pattern_file_count("#EXT-X-KEY:METHOD=AES-128,URI", m3u8.getAbsolutePath());
			if (count - 0 == 0) {
				// 3.获取所有的ts文件
				List<String> tsList = ShellCommon.grep_i_pattern_file_list(".ts", m3u8.getAbsolutePath());
				// String keypath = null;
				List<String> keyList = new ArrayList<>();
				if (tsList != null && tsList.size() > 0) {
					int index = 0;
					for (String ts : tsList) {
						// 3.1ts在哪一行
						// grep -in ts file|awk -F : '{print $1}'
						int number = ShellCommon.grep_in_word_file_number(ts, m3u8.getAbsolutePath());
						// 3.2生成key文件
						if ((index++) % FfmpegMain.modValue == 0) {
							// keypath = ShellCommon.openssl_rand_16(m3u8.getParent());
							keyList = RedisUtils.getM3u8KeyPrefix(new Random().nextInt(50000));
						}
						// 3.3加密
						String iv = UUID.randomUUID().toString().replaceAll("-", "");
						openssl_aes128_for_ts(m3u8.getParent(), ts, iv, keyList.get(1));
						// 3.4 插入信息在某一行
						// n是哪一行,i是参数不需要动,....是要插入的内容
						// sed 'ni....' m3u8
						ShellCommon.sed_insert_info(m3u8File, number - 1, "#EXT-X-KEY:METHOD=AES-128,URI=\"" + PropertiesUtils.getProperty("ffmpeg_uri") + keyList.get(0) + "\",IV=0x" + iv);
					}
				}
			}
		}

		/**
		 * 加密
		 * 
		 * @param m3u8File
		 * @param modValue
		 *            每隔modValue个ts加一次密
		 * @return
		 */
		public static String m3u8_cryption(String m3u8File) {
			// 1.判断是否存在
			File m3u8 = new File(m3u8File);
			if (!m3u8.exists()) {
				System.err.println("m3u8文件：" + m3u8.getAbsolutePath() + "不存在");
				return "error";
			}
			// 2.判断是否曾经加密过
			// grep -i #EXT-X-KEY:METHOD=AES-128,URI files
			Integer count = ShellCommon.grep_i_pattern_file_count("#EXT-X-KEY:METHOD=AES-128,URI", m3u8.getAbsolutePath());
			if (count - 0 == 0) {
				// 3.获取所有的ts文件
				List<String> tsList = ShellCommon.grep_i_pattern_file_list(".ts", m3u8.getAbsolutePath());
				String keypath = null;
				if (tsList != null && tsList.size() > 0) {
					int index = 0;
					for (String ts : tsList) {
						// 3.1ts在哪一行
						// grep -in ts file|awk -F : '{print $1}'
						int number = ShellCommon.grep_in_word_file_number(ts, m3u8.getAbsolutePath());
						// 3.2生成key文件
						if ((index++) % FfmpegMain.modValue == 0) {
							keypath = ShellCommon.openssl_rand_16(m3u8.getParent());
						}
						// 3.3加密
						String iv = UUID.randomUUID().toString().replaceAll("-", "");
						openssl_aes128_for_ts(m3u8.getParent(), ts, iv, ShellCommon.hexdump_16(keypath));
						// 3.4 插入信息在某一行
						// n是哪一行,i是参数不需要动,....是要插入的内容
						// sed 'ni....' m3u8
						ShellCommon.sed_insert_info(m3u8File, number - 1, "#EXT-X-KEY:METHOD=AES-128,URI=\"" + new File(keypath).getName() + "\",IV=0x" + iv);
					}
				}
			} else {
				System.err.println(m3u8.getAbsolutePath() + "已经加密过了");
			}
			return null;
		}

		/**
		 * 加密
		 * 
		 * @param tsDir
		 * @param tsName
		 * @param index
		 * @param encryptionKey
		 */
		public static void openssl_aes128_for_ts(String tsDir, String tsName, String iv, String encryptionKey) {
			File tsFile = new File(tsDir, tsName);
			// 加密
			// openssl aes-128-cbc -e -in oldtsfile -out newtsfile -nosalt -iv 16_int -K encryptionKey
			String command = "openssl aes-128-cbc -e -in " + tsFile.getAbsolutePath() + " -out " + tsFile.getAbsolutePath() + ".swp" + " -nosalt -iv " + iv + " -K " + encryptionKey;
			// String command = "openssl aes-128-cbc -e -in " + tsFile.getAbsolutePath() + " -out " + tsFile.getAbsolutePath() + ".swp" + " -nosalt -k " + encryptionKey;
			Shell.execCommand(command, String.class);
			// 删除原文件
			String command_rm = "rm -f " + tsFile.getAbsolutePath();
			Shell.execCommand(command_rm, String.class);
			// 重命名
			String command_mv = "mv -f " + tsFile.getAbsolutePath() + ".swp " + tsFile.getAbsolutePath();
			Shell.execCommand(command_mv, String.class);
		}

		/**
		 * mp4文件转换成hls
		 * 
		 * @param mp4Dir
		 *            mp4所在的目录
		 * @param mp4Name
		 *            mp4名称
		 * @param m3u8Dir
		 *            m3u8输出的目录
		 * @param m3u8Name
		 *            m3u8的名称
		 * @param tsPrefix
		 *            生成ts的前缀
		 * @return
		 */
		public static String ffmpeg_mp4_to_hls(String mp4Dir, String mp4Name, String m3u8Dir, String m3u8Name, String tsPrefix) {
			// ffmpeg -i $input_file -codec copy -map 0 -f segment -vbsf h264_mp4toannexb -segment_list_size 0 -segment_list liste.m3u8 -segment_time 10 $ts_prefix%d.ts
			// 初始化目录
			ShellCommon.mkdir_p(m3u8Dir);
			List<String> commandList = new ArrayList<>();
			commandList.add("ffmpeg");
			commandList.add("-i");
			commandList.add(new File(mp4Dir, mp4Name.toLowerCase().endsWith(".mp4") ? mp4Name : (mp4Name + ".mp4")).getAbsolutePath());// 文件
			commandList.add("-codec");
			commandList.add("copy");
			commandList.add("-map");
			commandList.add("0");
			commandList.add("-f");
			commandList.add("segment");
			commandList.add("-vbsf");
			commandList.add("h264_mp4toannexb");
			commandList.add("-segment_list_size");
			commandList.add("0");
			commandList.add("-segment_list");
			commandList.add(new File(m3u8Dir, m3u8Name.toLowerCase().endsWith(".m3u8") ? m3u8Name : (m3u8Name + ".m3u8")).getAbsolutePath());// m3u8文件
			commandList.add("-segment_time");
			commandList.add("10");
			commandList.add(new File(m3u8Dir, tsPrefix + "%d.ts").getAbsolutePath());// ts的名字
			// 执行命令
			String result = Shell.execCommand(commandList, String.class);
			return result;
		}

	}

	/**
	 * shell的一些常用命令
	 * 
	 * @author loujie
	 *
	 */
	public static class ShellCommon {
		/**
		 * 创建目录
		 * 
		 * @param dir
		 */
		public static void mkdir_p(String dir) {
			File dirFile = new File(dir);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
		}

		/**
		 * 从一个文件中查找符合条件
		 * 
		 * @param word
		 *            单词
		 * @param file
		 *            指定文件
		 * @return
		 */
		public static int grep_in_word_file_number(String word, String file) {
			String command = "grep -in " + word + " " + file + " | head -1 | awk -F : '{print $1}'";
			return Shell.execCommand(command, Integer.class);
		}

		/**
		 * 从一个文件中查找符合条件的行信息
		 * 
		 * @param pattern
		 * @param file
		 * @return
		 */
		public static List<String> grep_i_pattern_file_list(String pattern, String file) {
			String command = "grep -i '" + pattern + "' " + file;
			return Shell.execCommand(command);
		}

		/**
		 * 向指定的文件中的指定行插入信息
		 * 
		 * @param file
		 * @param number
		 * @param info
		 */
		public static void sed_insert_info(String file, int number, String info) {
			String command = "sed '" + number + "i" + info + "' " + file + " > " + file + ".swp";
			Shell.execCommand(command, String.class);
			String command_mv = "mv -f " + file + ".swp " + file;
			Shell.execCommand(command_mv, String.class);
		}

		/**
		 * 查看pattern在file中出现过的次数
		 * 
		 * @param pattern
		 * @param file
		 * @return
		 */
		public static int grep_i_pattern_file_count(String pattern, String file) {
			String command = "grep -i '" + pattern + "' " + file + " | wc -l";
			Integer count = Shell.execCommand(command, Integer.class);
			if (count == null) {
				count = 0;
			}
			return count;
		}

		/**
		 * 创建Key
		 * 
		 * @param parentDir
		 *            创建key的目录
		 * @return
		 */
		public static String openssl_rand_16(String parentDir) {
			File key = null;
			do {
				String uuid = UUID.randomUUID().toString();
				key = new File(parentDir, uuid + ".key");
			} while (key.exists());
			// openssl rand 16 > file
			String command = "openssl rand 16 > " + key.getAbsolutePath();
			Shell.execCommand(command, String.class);
			return key.getAbsolutePath();
		}

		/**
		 * 获取16进制的key
		 * 
		 * @param keyPath
		 * @return
		 */
		public static String hexdump_16(String keyPath) {
			// cat keyfile | hexdump -e '16/1 "%02x"'
			String command = "cat " + keyPath + " | hexdump -e '16/1 \"%02x\"'";
			return Shell.execCommand(command, String.class);
		}

		/**
		 * 下载文件到指定目录
		 * 
		 * @param url
		 * @param outdir
		 */
		public static void wget_P(String url, String outdir) {
			String command = "wget -cP " + outdir + " " + url;
			Shell.execCommand(command);
		}

	}

	/**
	 * 执行shell命令及一些常用的命令
	 * 
	 * @author loujie
	 *
	 */
	public static class Shell {

		/**
		 * 对外提供接口
		 * 
		 * @param command
		 * @param cla
		 * @return
		 */
		public static <T> T execCommand(String command, Class<T> cla) {
			List<String> commandList = new ArrayList<>();
			commandList.add(command);
			return execCommand(commandList, cla);
		}

		/**
		 * 对外提供接口
		 * 
		 * @param command
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static List<String> execCommand(String command) {
			return (List<String>) execCommand(createCommand(command), false);
		}

		/**
		 * 对外提供接口
		 * 
		 * @param command
		 * @param cla
		 * @return
		 */
		public static <T> T execCommand(List<String> commandList, Class<T> cla) {
			List<String> commands = createCommand(convertCommand(commandList));
			Object result = execCommand(commands);
			try {
				return cla.getConstructor(String.class).newInstance(result);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		/**
		 * 执行shell命令,
		 * 
		 * @param command
		 * @return
		 */
		private static Object execCommand(List<String> command, boolean... returnStrs) {
			if (returnStrs == null || returnStrs.length == 0) {
				returnStrs = new boolean[]{true};
			}
			boolean returnStr = returnStrs[0];
			StringBuilder stringBuilder = new StringBuilder();
			List<String> returnList = new ArrayList<>();
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			// 合并in,err
			processBuilder.redirectErrorStream(true);
			try {
				// 执行命令
				Process process = processBuilder.start();
				BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(process.getInputStream()), "GB2312"));
				String str = null;
				long i = 0;
				do {
					str = reader.readLine();
					if (str != null) {
						if (i > 0) {
							stringBuilder.append("\r\n");
						}
						stringBuilder.append(str);
						returnList.add(str);
						i++;
					}
				} while (str != null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (returnStr)
				return stringBuilder.toString();
			else
				return returnList;
		}

		/**
		 * 生成shell命令
		 * 
		 * @param commands
		 * @return
		 */
		private static List<String> createCommand(String command) {
			List<String> execCommand = new ArrayList<>();
			execCommand.add("/bin/sh");
			execCommand.add("-c");

			execCommand.add(command);
			return execCommand;
		}

		/**
		 * 集合转换字符
		 * 
		 * @param commandList
		 * @return
		 */
		private static String convertCommand(List<String> commandList) {
			StringBuilder stringBuilder = new StringBuilder(Arrays.toString(commandList.toArray()));
			stringBuilder.deleteCharAt(0).reverse().deleteCharAt(0).reverse();
			return stringBuilder.toString().replace(", ", " ");
		}

	}

}
