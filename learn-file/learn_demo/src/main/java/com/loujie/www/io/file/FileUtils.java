package com.loujie.www.io.file;

import java.io.Closeable;
import java.io.IOException;

public class FileUtils {

	/**
	 * 关闭流
	 * 
	 * @param closeables
	 */
	public static void close(Closeable... closeables) {
		if (closeables != null && closeables.length > 0) {
			for (Closeable closeable : closeables) {
				try {
					if (closeable != null) {
						closeable.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
