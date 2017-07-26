package com.loujie.www.file;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件/目录的一些操作
 * 
 * @author loujie
 *
 */
public class FileUtils {

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
