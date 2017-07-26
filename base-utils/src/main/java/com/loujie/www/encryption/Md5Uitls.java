package com.loujie.www.encryption;

import java.security.MessageDigest;

/**
 * md5加密/及扩展方法
 * 
 * @author loujie
 *
 */
public class Md5Uitls {

	/**
	 * md5加密
	 * 
	 * @param mdstr
	 * @return
	 */
	public static String md5(Object mdstr) {
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

		try {
			byte[] btInput = String.valueOf(mdstr).getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 混合两个长度相同的字符串
	 * 
	 * @param oneStr
	 *            需要混合的字符串1
	 * @param twoStr
	 *            需要混合的字符串2
	 * @return
	 */
	public static String mixedString(String oneStr, String twoStr) {
		StringBuilder stringBuilder = new StringBuilder();
		if (oneStr.length() - twoStr.length() == 0) {
			for (int i = 0; i < oneStr.length(); i++) {
				stringBuilder.append(oneStr.charAt(i));
				stringBuilder.append(twoStr.charAt(i));
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 分开一个字符串,根据奇数/偶数
	 * 
	 * @param oneStr
	 *            需分开字符,必须是偶数长度
	 * @param isOdd
	 *            是否为奇数
	 * @return
	 */
	public static String oddEven(String oneStr, boolean isOdd) {
		StringBuilder stringBuilder = new StringBuilder();
		if (oneStr.length() % 2 == 0) {
			int i = 0;
			if (isOdd) {
				i = 1;
			}
			for (; i < oneStr.length(); i = i + 2) {
				stringBuilder.append(oneStr.charAt(i));
			}
		}
		return stringBuilder.toString();
	}

}
