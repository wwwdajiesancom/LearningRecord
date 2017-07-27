package com.loujie.www.encryption;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Sha1Util {

	/**
	 * SHA1 安全加密算法
	 * 
	 * @param maps
	 *            参数key-value map集合
	 * @return
	 * @throws DigestException
	 */
	public static String SHA1(Map<String, Object> maps) {
		// 获取信息摘要 - 参数字典排序后字符串
		String decrypt = getOrderByUrl(maps);
		try {
			// 指定sha1算法
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(decrypt.getBytes());
			// 获取字节数组
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString().toUpperCase();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	private static String getOrderByUrl(Map<String, Object> maps) {
		List<String> list = lexicographicOrder(getParamsName(maps));
		StringBuilder sBuilder = new StringBuilder();
		for (String item : list) {
			sBuilder.append(item + "=" + maps.get(item) + "&");
		}
		if (sBuilder.length() > 0) {
			sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
		}
		return sBuilder.toString();
	}

	/**
	 * 获取参数名称 key
	 * 
	 * @param maps
	 *            参数key-value map集合
	 * @return
	 */
	private static List<String> getParamsName(Map<String, Object> maps) {
		List<String> paramNames = new ArrayList<String>();
		for (Map.Entry<String, Object> entry : maps.entrySet()) {
			paramNames.add(entry.getKey());
		}
		return paramNames;
	}

	/**
	 * 参数名称按字典排序
	 * 
	 * @param paramNames
	 *            参数名称List集合
	 * @return 排序后的参数名称List集合
	 */
	private static List<String> lexicographicOrder(List<String> paramNames) {
		Collections.sort(paramNames);
		return paramNames;
	}

}
