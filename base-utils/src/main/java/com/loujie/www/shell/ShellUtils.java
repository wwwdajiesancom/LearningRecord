package com.loujie.www.shell;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 执行shell命令 一些基础命令的调用
 * 
 * @author loujie
 *
 */
public class ShellUtils {

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
			List<String> commandList = new ArrayList<String>();
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
			List<String> returnList = new ArrayList<String>();
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

	}

}
