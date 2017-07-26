package com.loujie.www.ffmpeg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.loujie.www.shell.ShellUtils.Shell;
import com.loujie.www.shell.ShellUtils.ShellCommon;

/**
 * ffmpeg的一些调用
 * 
 * @author loujie
 *
 */
public class FfmpegUtils {

	/**
	 * 加密
	 * 
	 * @param m3u8File
	 * @param modValue
	 *            每隔modValue个ts加一次密
	 * @return
	 */
	public static String m3u8_cryption(String m3u8File, int modValue) {
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
					if (index % modValue == 0)
						keypath = ShellCommon.openssl_rand_16(m3u8.getParent());
					// 3.3加密
					openssl_aes128_for_ts(m3u8.getParent(), ts, index++, ShellCommon.hexdump_16(keypath));
					// 3.4 插入信息在某一行
					// n是哪一行,i是参数不需要动,....是要插入的内容
					// sed 'ni....' m3u8
					ShellCommon.sed_insert_info(m3u8File, number - 1, "#EXT-X-KEY:METHOD=AES-128,URI=\"" + new File(keypath).getName() + "\"");
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
	public static void openssl_aes128_for_ts(String tsDir, String tsName, Integer index, String encryptionKey) {
		File tsFile = new File(tsDir, tsName);
		// 加密
		// openssl aes-128-cbc -e -in oldtsfile -out newtsfile -nosalt -iv 16_int -K encryptionKey
		String command = "openssl aes-128-cbc -e -in " + tsFile.getAbsolutePath() + " -out " + tsFile.getAbsolutePath() + ".swp" + " -nosalt -iv " + Integer.toHexString(index) + " -K "
				+ encryptionKey;
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
