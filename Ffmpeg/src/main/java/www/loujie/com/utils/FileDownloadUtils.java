package www.loujie.com.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloadUtils {

	/**
	 * 一次下载字节数
	 */
	private static final int BUFFER_LENGTH = 1024 * 4;

	/**
	 * 字符类型
	 */
	// private static final String _INPUT_CHARSET = "utf-8";

	/**
	 * 下载网络文件
	 * 
	 * @param networkUrl
	 *            网络地址
	 * @param fileName
	 *            文件名称,没有后缀
	 * @param outpath
	 *            文件输出的位置
	 */
	public static void downNetworkResource(String networkUrl, String fileName, String outpath) {
		URL url;
		try {
			// 1.远程连接
			url = new URL(networkUrl);
			String file_suffix = getSuffix(networkUrl, ".");
			fileName = fileName + file_suffix;
			// 2.
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(outpath, fileName)));
			// 3.下载
			copyInputToOutput(url.openStream(), bos);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取
	 * 
	 * @param path
	 *            文件路径
	 * @param sep
	 *            分隔符
	 * @return
	 * @throws Exception
	 */
	private static String getSuffix(String path, String sep) throws Exception {
		int last_index = path.lastIndexOf(sep);
		if (last_index == -1) {
			throw new Exception("错误的网络url");
		}
		String file_suffix = path.substring(last_index);
		return file_suffix;
	}

	/**
	 * copy输入流到输出流
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 */
	private static void copyInputToOutput(InputStream is, OutputStream os) {
		OutputStream _oStream = null;
		try {
			_oStream = new BufferedOutputStream(os);
			byte[] buf = new byte[BUFFER_LENGTH];
			int len = -1;
			while ((len = is.read(buf)) != -1) {
				_oStream.write(buf, 0, len);
			}
		} catch (IOException e) {
		} finally {
			try {
				if (_oStream != null) {
					_oStream.flush();
				}
			} catch (IOException e) {
			}
			try {
				if (_oStream != null) {
					_oStream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 获取网络资源的字节长度
	 * 
	 * @param url
	 *            网络资源
	 * @return
	 */
	public static long getLength(URL url) {
		long length = 0;
		URLConnection urlConnection;
		try {
			urlConnection = url.openConnection();
			long size = urlConnection.getContentLengthLong();
			length = size;
		} catch (IOException e) {
		}
		return length;
	}

}
