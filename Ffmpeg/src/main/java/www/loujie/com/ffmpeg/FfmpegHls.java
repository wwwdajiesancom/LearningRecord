package www.loujie.com.ffmpeg;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class FfmpegHls {

	private static Scanner scanner;

	public static void main(String[] args) throws InterruptedException {
		scanner = new Scanner(System.in);
		System.out.println("#请输入以下值:");
		System.out.println("#A=mp4生成m3u8文件");
		System.out.println("#B=对m3u8进行加密");
		System.err.println("#C=查找文件中是否包含某个信息");
		System.err.println("#D=退出");
		System.out.println("###########");
		String tag = scanner.nextLine();
		if (tag == null || tag.isEmpty()) {
			tag = "A";
		}
		switch (tag.toUpperCase()) {
			case "A" :
				mp4_to_hls();
				break;
			case "C" :
				System.out.print("请输入要查找的信息:");
				String pattern = scanner.nextLine();
				System.out.print("请输入要从那个文件中查找,全路径:");
				String file = scanner.nextLine();
				ShellExe.grep_i_pattern_file_count(pattern, file);
				break;
			case "B" :
				m3u8();
			default :
				return;
		}
	}

	/**
	 * 对已经存在的m3u8的文件进行加密
	 */
	private static void m3u8() {
		System.out.print("请输入m3u8文件的全路径:");
		String m3u8File = scanner.nextLine();
		FfmpegUtils.m3u8_jiami(m3u8File);
	}

	/**
	 * MP4转换成m3u8
	 */
	private static void mp4_to_hls() {
		System.out.print("请输入MP4文件的目录:");
		String mp4Dir = scanner.nextLine();
		System.out.print("请输入MP4文件的名称:");
		String mp4Name = scanner.nextLine();
		System.out.print("请输入要生成m3u8文件的目录:");
		String m3u8Dir = scanner.nextLine();
		System.out.print("请输入要生成m3u8文件的名称:");
		String m3u8Name = scanner.nextLine();
		System.out.print("请输入ts文件的前缀:");
		String tsPrefix = scanner.nextLine();
		System.err.println(new File(mp4Dir, mp4Name).getAbsolutePath());
		File m3u8 = new File(m3u8Dir, m3u8Name);
		System.err.println(m3u8.getAbsolutePath());
		System.err.println(tsPrefix);
		String result = FfmpegUtils.ffmpeg_mp4_to_hls(mp4Dir, mp4Name, m3u8Dir, m3u8Name, tsPrefix);

		System.err.println(result);
	}

}

class FfmpegUtils {

	public static String m3u8_jiami(String m3u8File) {
		// 1.判断是否存在
		File m3u8 = new File(m3u8File);
		if (!m3u8.exists()) {
			System.err.println("m3u8文件：" + m3u8.getAbsolutePath() + "不存在");
			return "error";
		}
		// 2.判断是否曾经加密过
		// grep -i #EXT-X-KEY:METHOD=AES-128,URI files
		Integer count = ShellExe.grep_i_pattern_file_count("#EXT-X-KEY:METHOD=AES-128,URI", m3u8.getAbsolutePath());
		if (count - 0 == 0) {
			// 3.获取所有的ts文件
			List<String> tsList = ShellExe.grep_i_pattern_file_list(".ts", m3u8.getAbsolutePath());
			String keypath = null;
			if (tsList != null && tsList.size() > 0) {
				int index = 0;
				for (String ts : tsList) {
					// 3.1ts在哪一行
					// grep -in ts file|awk -F : '{print $1}'
					int number = ShellExe.grep_in_word_file_number(ts, m3u8.getAbsolutePath());
					// 3.2生成key文件
					if (index % 4 == 0)
						keypath = ShellExe.create_key(m3u8.getParent());
					// 3.3加密
					ShellExe.jia_mi(m3u8.getParent(), ts, index++, ShellExe.get_key(keypath));
					// 3.4 插入信息在某一行
					// n是哪一行,i是参数不需要动,....是要插入的内容
					// sed 'ni....' m3u8
					ShellExe.sed_insert(m3u8File, number - 1, "#EXT-X-KEY:METHOD=AES-128,URI=\"" + new File(keypath).getName() + "\"");
				}
			}
		} else {
			System.err.println(m3u8.getAbsolutePath() + "已经加密过了");
		}
		return null;
	}

	/**
	 * mp4文件转换成hls
	 * 
	 * @param mp4Dir
	 * @param mp4Name
	 * @param m3u8Dir
	 * @param m3u8Name
	 * @param tsPrefix
	 * @return
	 */
	public static String ffmpeg_mp4_to_hls(String mp4Dir, String mp4Name, String m3u8Dir, String m3u8Name, String tsPrefix) {
		// ffmpeg -i $input_file -codec copy -map 0 -f segment -vbsf h264_mp4toannexb -segment_list_size 0 -segment_list liste.m3u8 -segment_time 10 $ts_prefix%d.ts
		// 初始化目录
		ShellExe.initDir(m3u8Dir);
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
		String result = ShellExe.exec(commandList, String.class);
		return result;
	}

}

/**
 * 执行Shell命令类
 * 
 * @author loujie
 *
 */
class ShellExe {

	/**
	 * 创建Key
	 * 
	 * @param parentDir
	 *            创建key的目录
	 * @return
	 */
	public static String create_key(String parentDir) {
		File key = null;
		do {
			String uuid = UUID.randomUUID().toString();
			key = new File(parentDir, uuid + ".key");
		} while (key.exists());
		// openssl rand 16 > file
		String command = "openssl rand 16 > " + key.getAbsolutePath();
		System.err.println("生成key：" + exec(command, String.class));
		return key.getAbsolutePath();
	}

	/**
	 * 获取16进制的key
	 * 
	 * @param keyPath
	 * @return
	 */
	public static String get_key(String keyPath) {
		// cat keyfile | hexdump -e '16/1 "%02x"'
		String command = "cat " + keyPath + " | hexdump -e '16/1 \"%02x\"'";
		return exec(command, String.class);
	}

	/**
	 * 加密
	 * 
	 * @param tsDir
	 * @param tsName
	 * @param index
	 * @param encryptionKey
	 */
	public static void jia_mi(String tsDir, String tsName, Integer index, String encryptionKey) {
		File tsFile = new File(tsDir, tsName);
		// openssl aes-128-cbc -e -in oldtsfile -out newtsfile -nosalt -iv 16_int -K encryptionKey
		String command = "openssl aes-128-cbc -e -in " + tsFile.getAbsolutePath() + " -out " + tsFile.getAbsolutePath() + ".swp" + " -nosalt -iv " + Integer.toHexString(index) + " -K "
				+ encryptionKey;
		System.err.println(exec(command, String.class));
		String command_rm = "rm -f " + tsFile.getAbsolutePath();
		System.err.println(exec(command_rm, String.class));
		String command_mv = "mv " + tsFile.getAbsolutePath() + ".swp " + tsFile.getAbsolutePath();
		System.err.println(exec(command_mv, String.class));
	}

	/**
	 * 查看pattern是否在file中出现过
	 * 
	 * @param pattern
	 * @param file
	 * @return
	 */
	public static int grep_i_pattern_file_count(String pattern, String file) {
		String command = "grep -i '" + pattern + "' " + file + " | wc -l";
		Integer count = exec(command, Integer.class);
		if (count == null) {
			count = 0;
		}
		return count;
	}

	/**
	 * 插入信息给文件中
	 * 
	 * @param file
	 * @param number
	 * @param info
	 */
	public static void sed_insert(String file, int number, String info) {
		String command = "sed '" + number + "i" + info + "' " + file + " > " + file + ".swp";
		System.err.println(exec(command, String.class));
		String command_mv = "mv -f " + file + ".swp " + file;
		System.out.println(exec(command_mv, String.class));
	}

	/**
	 * word在file中的第几行
	 * 
	 * @param word
	 *            单词
	 * @param file
	 *            指定文件
	 * @return
	 */
	public static int grep_in_word_file_number(String word, String file) {
		String command = "grep -in " + word + " " + file + " | awk -F : '{print $1}'";
		return exec(command, Integer.class);
	}

	/**
	 * 
	 * @param pattern
	 * @param file
	 * @return
	 */
	public static List<String> grep_i_pattern_file_list(String pattern, String file) {
		String command = "grep -i '" + pattern + "' " + file;
		return exec(command);
	}

	/**
	 * 初始化目录
	 * 
	 * @param dir
	 */
	public static void initDir(String dir) {
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	}

	/**
	 * 对外提供接口
	 * 
	 * @param command
	 * @param cla
	 * @return
	 */
	public static <T> T exec(String command, Class<T> cla) {
		List<String> commandList = new ArrayList<>();
		commandList.add(command);
		return exec(commandList, cla);
	}

	/**
	 * 对外提供接口
	 * 
	 * @param command
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> exec(String command) {
		return (List<String>) execCommand(createCommand(command), false);
	}

	/**
	 * 对外提供接口
	 * 
	 * @param command
	 * @param cla
	 * @return
	 */
	public static <T> T exec(List<String> commandList, Class<T> cla) {
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

	/**
	 * 执行shell
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

}
