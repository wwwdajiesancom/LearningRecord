package com.loujie.www.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class IoDemo {

	String filePath = "D:\\logss\\dir\\ArgsUtils.java";// 原文件路径
	String descFilePath = "D:\\logss\\dircopy\\ArgsUtils.java";// 目的路径
	@Test
	public void copyDemo() {
		// 1.找到需要转移的文件
		File file = new File(filePath);
		InputStream is = null;
		OutputStream os = null;
		try {
			// 2.选择输入流
			is = new FileInputStream(file);
			// 选择输出流
			os = new FileOutputStream(descFilePath, false);
			// 3.读取流信息
			// 定义一次读取最大字节数
			byte[] bytes = new byte[2];
			int i = -1;
			do {
				i = is.read(bytes);
				// 当为-1的时候不写入,结束循环
				if (i != -1) {
					os.write(bytes, 0, i);
				}
			} while (i != -1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// copy一个目录的信息到另一个目录中去
	@Test
	public void copyDirectory() {
		String oldDirPath = "D:\\logss\\dir";
		String newDirPath = "D:\\logss\\copydir";

		File oldFile = new File(oldDirPath);
		File newFile = new File(newDirPath);
		// 1.创建目录
		if (newFile.exists()) {
			newFile.mkdirs();
		}
		// 2.遍历目录
		forDirectory(oldFile, oldDirPath, newDirPath);
	}

	// 循环目录,并copy里面的文件
	public void forDirectory(File srcFile, String oldDirPath, String newDirPath) {
		// 1.考虑是文件,结束
		if (srcFile.isFile()) {
			copyFile(srcFile, srcFile.getParent().replace(oldDirPath, newDirPath));
			return;
		}
		// 2.同样是目录,需要循环
		if (srcFile.listFiles() != null) {
			for (File itemFile : srcFile.listFiles()) {
				this.forDirectory(itemFile, oldDirPath, newDirPath);
			}
		}
	}

	// 拷贝文件
	public void copyFile(File srcFile, String descFileDirPath) {
		// 1.创建目地文件路径
		File descFile = new File(descFileDirPath);
		if (!descFile.exists()) {
			descFile.mkdirs();
		}
		// 2.输入流,输出流
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(srcFile);
			os = new FileOutputStream(new File(descFile, srcFile.getName()));
			// 每次读取大小
			byte[] bytes = new byte[1024];
			int i = -1;
			do {
				i = is.read(bytes);
				if (i != -1) {
					os.write(bytes, 0, i);
				}
			} while (i != -1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
