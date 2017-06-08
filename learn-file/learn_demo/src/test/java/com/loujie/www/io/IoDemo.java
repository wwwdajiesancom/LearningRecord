package com.loujie.www.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
		String oldDirPath = "D:\\桌面临时文件\\88bbcn\\template";
		String newDirPath = "D:\\logss\\copydir2";

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
		// 创建一些空目录
		File descFile = new File(srcFile.getAbsolutePath().replace(oldDirPath, newDirPath));
		if (!descFile.exists()) {
			descFile.mkdirs();
		}
		return;
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
			is = new BufferedInputStream(new FileInputStream(srcFile));// 先打开的后关闭
			os = new BufferedOutputStream(new FileOutputStream(new File(descFile, srcFile.getName())));
			// 每次读取大小
			byte[] bytes = new byte[1024];
			int i = -1;
			do {
				i = is.read(bytes);
				if (i != -1) {
					os.write(bytes, 0, i);
				}
			} while (i != -1);
			os.flush();
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

	// 拷贝文件,字符流
	@Test
	public void charDemo() {
		// 定义路径
		String srcPath = "D:\\logss\\dir";
		String descPath = "D:\\logss\\copydir3";
		File oldFile = new File(srcPath);
		File newFile = new File(descPath);
		// 1.创建目录
		if (newFile.exists()) {
			newFile.mkdirs();
		}
		// 2.遍历目录
		forDirectory2(oldFile, srcPath, descPath);
	}

	// 循环目录,并copy里面的文件
	public void forDirectory2(File srcFile, String oldDirPath, String newDirPath) {
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
		// 创建一些空目录
		File descFile = new File(srcFile.getAbsolutePath().replace(oldDirPath, newDirPath));
		if (!descFile.exists()) {
			descFile.mkdirs();
		}
		return;
	}

	// 通过字符流拷贝文件
	public void copyFileByCharStream(File srcFile, String descFileDirPath) {
		// 1.创建目地文件路径
		File descFile = new File(descFileDirPath);
		if (!descFile.exists()) {
			descFile.mkdirs();
		}
		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(srcFile));
			w = new BufferedWriter(new FileWriter(new File(descFile, srcFile.getName())));
			String tempStr = null;
			do {
				tempStr = r.readLine();
				if (tempStr != null) {
					w.write(tempStr);
					w.newLine();
				}
			} while (tempStr != null);
			w.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (r != null) {
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testBuffered() {
		File srcFile = new File("D:\\logss\\dir\\ArgsUtils.java");
		copyFileByCharStream(srcFile, "D:\\logss\\dir\\ArgsUtils2.java");
	}
}
