package com.loujie.www.io;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileDemo {

	@Test
	public void fileTest1() {
		// 常量
		System.err.println(File.pathSeparator);// 路径分隔符 (;)
		System.err.println(File.separator);// 文件分割符(Windows=\,Linux=/)
	}

	String path = "D:\\logss\\2.jpg";
	@Test
	public void pathTest() throws IOException {
		File file = new File(path);
		// 1.常用方法,一些属性信息
		System.err.println(file.getName());// 文件名称 2.jpg
		System.err.println(file.getPath());// 如果path是决定路径,返回的就是绝对路径,否则就是相对路径 D:\logss\2.jpg
		System.err.println(file.getAbsolutePath());// 返回绝对路径 D:\logss\2.jpg
		System.err.println(file.getParent());// 返回上级目录,如果没有的话就是空 D:\logss
		System.err.println(file.length());// 文件长度,不存在的话为0
		// 2.判断信息
		System.err.println(file.exists());// 是否存在,目录或文件
		System.err.println(file.isDirectory());// 是否为目录
		System.err.println(file.isFile());// 是否为文件,如果不存在,默认为文件夹
		System.err.println(file.canExecute());// 是否可执行
		System.err.println(file.canRead());// 是否可读
		System.err.println(file.canWrite());// 是否可写
		// 3.创建,删除
		System.err.println(file.delete());// 删除文件
		System.err.println(file.createNewFile());// 创建文件
	}

	String path2 = "D:\\logss\\dir";
	@Test
	public void dirTest() {
		File dirFile = new File(path2);
		// 1.
		if (!dirFile.exists()) {
			dirFile.mkdirs();// 如果不存在,则创建目录
		}
		// 2.遍历出,文件夹中的所有文件
		forFiles(dirFile, "");
		// 3.遍历出盘符
		for (File itemFile : File.listRoots()) {
			forFiles(itemFile, "");
		}
	}
	// 遍历文件夹
	public void forFiles(File file, String prefixSep) {
		// 1.如果是文件的话,直接输出
		if (file.isFile()) {
			System.err.println(prefixSep + file.getAbsolutePath());
			return;
		}
		// 2.如果是目录的话,遍历
		System.err.println(prefixSep + file.getAbsolutePath());
		if (file.listFiles() != null) {
			for (File itemFile : file.listFiles()) {
				forFiles(itemFile, prefixSep + "    ");
			}
		}
	}
	
	

}
