package com.loujie.www.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import com.loujie.www.io.file.FileUtils;

/**
 * RandomAccessFile
 * 
 * @author loujie
 *
 */
public class RandomDemo {

	@Test
	public void partDemo() throws IOException {
		PartFile pf = new PartFile("D:\\logss\\dir\\PbsConstants.java", "D:\\logss\\dir5", 51);
		for (int i = 0; i < pf.getFileNum(); i++) {
			// 1.读取文件
			RandomAccessFile readRAF = new RandomAccessFile(new File(pf.getSrcFilePath()), "r");
			// 需要跳过的字节
			readRAF.seek(i * pf.getFilesize());
			// 每次读取的字节
			int bytes_len = 4;
			byte[] bytes = new byte[bytes_len];
			OutputStream os = new BufferedOutputStream(new FileOutputStream(new File(pf.getDescFileDir() + File.separator + pf.getFileNameList().get(i))));
			// 每个文件大小
			long filesize = pf.getFilesize();
			int j = 0;
			int total = 0;
			do {
				j = readRAF.read(bytes);
				if (j != -1) {
					total += j;
					if (total < filesize) {
						os.write(bytes, 0, j);
					} else {
						int c = (int) (bytes_len - (total - filesize));
						if (c != 0) {
							os.write(bytes, 0, c);
						}
						break;
					}
				}
			} while (j != -1);
			FileUtils.close(os, readRAF);
		}
	}

	@Test
	public void mergeDemo() throws IOException {
		// 1.文件的路径列表
		List<String> fileList = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			fileList.add("D:\\logss\\dir5\\PbsConstants.java" + i + ".part");
		}
		// 2.存放文件
		String descFilePath = "D:\\logss\\dir\\Abcd.java";
		RandomAccessFile rafWrite = new RandomAccessFile(new File(descFilePath), "rw");
		long seekIndex = 0;
		for (String itemSrc : fileList) {
			InputStream is = new BufferedInputStream(new FileInputStream(new File(itemSrc)));
			rafWrite.seek(seekIndex);
			seekIndex += is.available();
			System.err.println(seekIndex);
			byte[] bytes = new byte[4];
			int i = 0;
			do {
				i = is.read(bytes);
				if (i != -1) {
					rafWrite.write(bytes, 0, i);
				}
			} while (i != -1);
			System.err.println(is.available());
			FileUtils.close(is);
		}
		FileUtils.close(rafWrite);
	}

	@Test
	public void mergeDemo2() throws IOException {
		// 1.文件的路径列表
		Vector<InputStream> vector = new Vector<>();
		for (int i = 0; i < 11; i++) {
			vector.add(new BufferedInputStream(new FileInputStream(new File("D:\\logss\\dir5\\PbsConstants.java" + i + ".part"))));
		}
		SequenceInputStream sis = new SequenceInputStream(vector.elements());
		byte[] bytes = new byte[4];
		int i = 0;
		do {
			i = sis.read(bytes);
			if (i != -1) {

			}
		} while (i != -1);
		FileUtils.close(sis);
	}

}

/**
 * 拆分文件的基本属性
 * 
 * @author loujie
 *
 */
class PartFile implements Serializable {

	private static final long serialVersionUID = 4079863037486348943L;

	private String srcFilePath = "";// 文件路径

	private String descFileDir = "";// 要保存文件的路径

	private int fileNum = 0;// 文件数量

	private long filesize = 1024l;// 每个文件的大小

	private List<String> fileNameList = new ArrayList<>();// 文件名称列表

	public PartFile(String srcFilePath) {
		this(srcFilePath, new File(srcFilePath).getParent());
	}

	public PartFile(String srcFilePath, String descFileDir) {
		this(srcFilePath, descFileDir, 5);
	}

	public PartFile(String srcFilePath, long filesize) {
		this(srcFilePath, new File(srcFilePath).getParent(), filesize);
	}

	public PartFile(String srcFilePath, String descFileDir, long filesize) {
		// 1.源文件
		this.srcFilePath = srcFilePath;
		if (this.srcFilePath == null || this.srcFilePath.isEmpty()) {
			throw new RuntimeException("源文件地址不可为空");
		}
		File srcFile = new File(srcFilePath);
		if (!srcFile.exists()) {
			throw new RuntimeException("源文件不存在");
		}
		if (!srcFile.isFile()) {
			throw new RuntimeException("源文件不存在");
		}
		// 2.存放路径
		this.descFileDir = descFileDir;
		if (this.descFileDir == null || this.descFileDir.isEmpty()) {
			throw new RuntimeException("存放文件目录不存在");
		}
		File descFile = new File(this.descFileDir);
		if (!descFile.exists()) {
			descFile.mkdirs();
		}
		if (descFile.isFile()) {
			descFile.mkdirs();
		}
		// 3.文件个数,
		this.filesize = filesize;
		if (this.filesize <= 0) {
			throw new RuntimeException("文件大小不能小于1");
		}
		this.init();
	}

	/**
	 * 
	 */
	public void init() {
		File srcFile = new File(this.srcFilePath);
		this.fileNum = new Double(Math.ceil((srcFile.length() * 1.0d) / this.filesize)).intValue();
		this.initFileNames(srcFile.getName());
	}

	/**
	 * 生成文件名称
	 * 
	 * @param fileName
	 */
	public void initFileNames(String fileName) {
		this.fileNameList.clear();
		for (int i = 0; i < this.fileNum; i++) {
			this.fileNameList.add(fileName + i + ".part");
		}
	}

	public String getSrcFilePath() {
		return srcFilePath;
	}

	public String getDescFileDir() {
		return descFileDir;
	}

	public int getFileNum() {
		return fileNum;
	}

	public long getFilesize() {
		return filesize;
	}

	public List<String> getFileNameList() {
		return fileNameList;
	}

}
