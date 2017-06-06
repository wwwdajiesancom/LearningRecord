package com.loujie.www.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断参数是否合法
 * 
 * @author loujie
 * 
 */
public class ArgsUtils {

	/**
	 * 获取图片的Url,正式环境拼接
	 * 
	 * @param url
	 * @return
	 */
	public static String getImgUrl2(String url, String... defaultUrl) {
		String defaultUrl_ = null;
		if (defaultUrl != null && defaultUrl.length > 0)
			defaultUrl_ = defaultUrl[0];
		if (!isEmpty(url)) {
			if (url.startsWith("http://") || url.startsWith("https://")) {
				return url;
			} else {
				return "http://picture.pbsedu.com:80/" + url;
			}
		}
		return defaultUrl_;
	}

	/**
	 * 是否包含了汉字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isContainsChinese(String str) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;
	}

	/**
	 * 比较Date大小
	 * 
	 * @param smallDate
	 *            小
	 * @param bigDate
	 *            大
	 * @return 如果正确为true，否则为false
	 */
	public static boolean compareDate(Date smallDate, Date bigDate) {
		if (smallDate == null || bigDate == null) {
			return false;
		}
		if (smallDate.getTime() <= bigDate.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 与当前时间比较,较小的话就是true
	 * 
	 * @param smallDate
	 * @return
	 */
	public static boolean compareTo(Date smallDate) {
		try {
			if (Calendar.getInstance().getTime().compareTo(smallDate) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * Date转化一下格式
	 * 
	 * @param date
	 * @param format
	 * @return Date
	 */
	public static Date formatDate2(Date date, String format) {
		return ArgsUtils.parseDate(formatDate(date, format), format);
	}

	/**
	 * 格式化日期，根据不同的格式
	 * 
	 * @param date
	 *            时间
	 * @param format
	 *            格式[yyyy][-MM][-dd] [HH][:mm][:ss]
	 * @return String
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 转换成Date
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		if (isEmpty(dateStr)) {
			Date nowDate = Calendar.getInstance().getTime();
			dateStr = new SimpleDateFormat(format).format(nowDate);
		}
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			dateStr = new SimpleDateFormat("yyyy-MM-dd").format(dateStr);
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			} catch (ParseException e1) {
				return Calendar.getInstance().getTime();
			}
		}
	}

	/**
	 * 转换成Date
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format, Date returnDate) {
		if (isEmpty(dateStr)) {
			return returnDate;
		}
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			return returnDate;
		}
	}

	public static BigDecimal parseBigDecimal(String value, String... defaultV) {
		BigDecimal defaultBD = new BigDecimal("0");
		if (defaultV != null && defaultV.length > 0) {
			defaultBD = new BigDecimal(defaultV[0]);
		}
		if (isEmpty(value)) {
			return defaultBD;
		}
		try {
			BigDecimal returnV = new BigDecimal(value);
			if (returnV.doubleValue() < 0) {
				return defaultBD;
			}
			return returnV;
		} catch (Exception e) {
			return defaultBD;
		}
	}

	/**
	 * 转换成Long
	 * 
	 * @param obj
	 * @return
	 */
	public static Long parseLong(Object obj) {
		try {
			if (obj == null)
				return 0l;
			return Long.parseLong(obj.toString());
		} catch (Exception exc) {
			return 0l;
		}
	}

	/**
	 * 转换成Integer
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer parseInteger(Object obj, Integer... defaultV) {
		Integer default_v = 0;
		if (defaultV != null && defaultV.length > 0) {
			if (defaultV[0] == 0) {
				default_v = null;
			} else {
				default_v = defaultV[0];
			}
		}
		try {
			if (obj == null)
				return default_v;
			return Integer.parseInt(obj.toString());
		} catch (Exception exc) {
			return default_v;
		}
	}

	/**
	 * 转换成Double
	 * 
	 * @param obj
	 * @return
	 */
	public static Double parseDouble(Object obj, Double... defaultV) {
		Double returnV = 0.00;
		if (defaultV != null && defaultV.length > 0) {
			returnV = defaultV[0];
		}
		try {
			if (obj == null)
				return returnV;
			return Double.parseDouble(obj.toString());
		} catch (Exception exc) {
			return returnV;
		}
	}

	/**
	 * 转换成字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String parseString(Object obj, String... defaultV) {
		String _defaultV = "";
		if (defaultV != null && defaultV.length > 0) {
			_defaultV = defaultV[0];
		}
		try {
			if (obj == null) {
				return _defaultV;
			}
			return String.valueOf(obj);
		} catch (Exception exc) {
			return _defaultV;
		}
	}

	/**
	 * 给date增加天数
	 * 
	 * @param date
	 *            现在时间
	 * @param addDays
	 *            要增加几天,可以为正数/负数
	 * @return Date
	 */
	public static Date getDate(Date date, int addDays) {
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addDays);
		return calendar.getTime();
	}

	/**
	 * 给date增加分钟
	 * 
	 * @param date
	 *            现在时间
	 * @param addMinutes
	 *            要增加多少分钟
	 * @return Date
	 */
	public static Date getDateMinute(Date date, int addMinutes) {
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, addMinutes);
		return calendar.getTime();
	}

	/**
	 * 判断是否为空
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmpty(String args) {
		if (args == null || args.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断List是否为空
	 * 
	 * @param args
	 * @return
	 */
	public static boolean isEmpty(List<?> args) {
		if (args == null || args.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Map是否为空
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<String, ?> map) {
		if (map == null || map.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmpty(Long args) {
		if (args == null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmpty(Integer args) {
		if (args == null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmpty(Double args) {
		if (args == null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断String[]是否为空
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmpty(String[] args) {
		if (args == null || args.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为NULL,或负数
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmptyOrMinus(Integer args) {
		if (args == null) {
			return true;
		}
		if (args < 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为NULL,或负数
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmptyOrMinus(Long args) {
		if (args == null) {
			return true;
		}
		if (args < 0L) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为NULL,或负数
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmptyOrMinus(Double args) {
		if (args == null) {
			return true;
		}
		if (args < 0L) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为NULL,或小于当前时间
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isEmptyOrMinus(Date args) {
		if (args == null) {
			return true;
		}
		try {
			if (args.getTime() <= Calendar.getInstance().getTimeInMillis()) {
				return true;
			}
		} catch (Exception exc) {
			// 时间参数存在问题

			return true;
		}
		return false;
	}

	/**
	 * 获取小数点后有效数
	 * 
	 * @param val
	 * @param precision
	 *            小数后几位
	 * @return
	 */
	public static Double roundDouble(double val, int precision) {
		Double ret = null;
		try {
			double factor = Math.pow(10, precision);
			ret = Math.floor(val * factor) / factor;
		} catch (Exception e) {

		}
		return ret;
	}

	public static String filterPhotoUrl(String photoUrl) {
		try {
			if (photoUrl == null || photoUrl.isEmpty()) {
				return null;
			}
			return photoUrl.replace("\\\\", "/").replace("\\", "/");
		} catch (Exception exc) {
			return null;
		}
	}

	/**
	 * 替换字符串中的\r,\n
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceRN(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return str.replace("\r", "").replace("\n", "");
	}

	public static Object trim(String... strs) {
		if (strs == null || strs.length == 0)
			return "";
		LinkedHashSet<String> sets = new LinkedHashSet<String>();
		for (String str : strs) {
			if (isEmpty(str)) {
				sets.add("");
			} else {
				sets.add(str.trim());
			}
		}
		if (sets.size() == 1) {
			return sets.toArray()[0];
		}
		return sets.toArray();
	}

	public static List<String> toList(String[] args) {
		List<String> returnList = new ArrayList<String>();
		if (args != null && args.length > 0) {
			for (String obj : args) {
				if (!returnList.contains(trim(obj))) {
					returnList.add(trim(obj).toString());
				}
			}
		}
		return returnList;
	}

	/**
	 * 匹配正则,返回List,如果为null/空,就是不匹配
	 * 
	 * @param regex
	 *            正则表达式
	 * @param matcherStr
	 *            需要匹配的字符串
	 * @return List的长度,是groupCount=()的数量
	 */
	public static List<String> matcherRegex(String regex, String matcherStr) {
		List<String> result = null;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(matcherStr);
		if (matcher.matches()) {
			result = new ArrayList<>();
			for (int i = 1; i <= matcher.groupCount(); i++) {
				result.add(matcher.group(i));
			}
		}
		return result;
	}

	public static String toXml(Map<String, Object> map) {
		StringBuilder returnXml = new StringBuilder();
		returnXml.append("<xml>");
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			returnXml.append("<" + entry.getKey() + ">");
			returnXml.append(entry.getValue());
			returnXml.append("</" + entry.getKey() + ">");
		}
		returnXml.append("</xml>");
		return returnXml.toString();
	}

	/**
	 * 获取随机字符串
	 * 
	 * @param randomLength
	 *            字符串长度
	 * @return
	 */
	public static String getRandomStr(int randomLength) {
		String str_ = "qazxswedcvfrtgbnhyujmkiolpQAZXSWEDCVFRTGBNHYUJMKIOLP0987654321";
		StringBuilder stringBuilder = new StringBuilder();
		int str_len = str_.length();
		Random random = new Random();
		for (int i = 0; i < randomLength; i++) {
			stringBuilder.append(str_.charAt(random.nextInt(str_len)));
		}
		return stringBuilder.toString();
	}

	/**
	 * md5加密相关
	 * 
	 * @author loujie
	 *
	 */
	public static class MD5Utils {
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

	/**
	 * 对象序列化
	 * 
	 * @author loujie
	 *
	 */
	public static class SerializationUtil {

		private static final String TEMP_ENCODING = "ISO-8859-1";
		private static final String DEFAULT_ENCODING = "UTF-8";

		/**
		 * 序列化
		 * 
		 * @param object
		 * @return
		 */
		public static byte[] serialize(Object object) {
			ObjectOutputStream oos = null;
			ByteArrayOutputStream baos = null;
			try {
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				byte[] bytes = baos.toByteArray();
				return bytes;
			} catch (Exception e) {
			}
			return null;
		}

		/**
		 * 序列化
		 * 
		 * @param object
		 * @return
		 */
		public static String serializeToString(Object object) {
			ObjectOutputStream oos = null;
			ByteArrayOutputStream baos = null;
			try {
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				String result = baos.toString(TEMP_ENCODING);
				return URLEncoder.encode(result, DEFAULT_ENCODING);
			} catch (Exception e) {
			}
			return null;
		}

		/**
		 * 反序列化
		 * 
		 * @param <T>
		 * 
		 * @param bytes
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static <T> T unserialize(byte[] bytes, Class<T> cla) {
			ByteArrayInputStream bais = null;
			try {
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return (T) ois.readObject();
			} catch (Exception e) {

			}
			return null;
		}

		/**
		 * 反序列化
		 * 
		 * @param <T>
		 * 
		 * @param serialize
		 * @return
		 */
		public static <T> T unserialize(String serialize, Class<T> cla) {
			if (isEmpty(serialize)) {
				return null;
			}
			try {
				serialize = URLDecoder.decode(serialize, DEFAULT_ENCODING);
				return unserialize(serialize.getBytes(TEMP_ENCODING), cla);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
