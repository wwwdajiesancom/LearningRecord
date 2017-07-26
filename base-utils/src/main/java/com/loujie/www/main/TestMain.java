package com.loujie.www.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {

	private static Scanner scanner;

	public static void main(String[] args) throws InterruptedException {

		System.out.println("##################################################################################################  ");
		System.out.println("##1.使用chrome,猎豹,360兼容模式等浏览器;                                                            ");
		System.out.println("##2.进入开发者模式,快捷方式F12、或者[右键]-->[检查]、再或者百度                                     ");
		System.out.println("##3.然后在打开的tab中找到[Network]模块，再然后选择[All]                                             ");
		System.out.println("##4.稍微的等待,会出现一个Timing?...,选中它，复制它右边的Request URL的内容，粘贴到让你输入内容的位置 ");
		System.out.println("##5.其它的都是类似操作                                                                              ");
		System.out.println("##################################################################################################  ");
		Thread.sleep(100);
		scanner = new Scanner(System.in);
		System.out.print("请输入network_url:");
		String network_url = scanner.nextLine();
		do {
			if (network_url == null) {
				System.out.print("请输入新的network_url:");
				network_url = scanner.nextLine();
			}
			try {
				// 获取播放到那里了
				Integer currentTimespan = Integer.parseInt(regexFind("CurrentTimespan=([0-9]*)", network_url).get(1));
				// 去掉部分字符串
				network_url = network_url.replaceAll("&CurrentTimespan=[0-9]*", "");
				// 调用接口
				do {
					currentTimespan = currentTimespan + 30;
					String result = http_request(network_url + "&" + currentTimespan, RequestMethod.GET);
					System.err.println(result);
					String process = regexFind("\"Process\":([0-9]*)", result).get(1);
					if ("100".equals(process)) {
						throw new RuntimeException("本节课程学习完成了......");
					}
					Thread.sleep(100);
				} while (true);
			} catch (RuntimeException e) {
				network_url = null;
				System.err.println(e.getMessage());
			} catch (Exception e) {
				network_url = null;
				e.printStackTrace();
				System.err.println("输入的地址异常.");
				System.err.print("请输入A=继续,其它结束");
				String esc = scanner.nextLine();
				if (esc == null || esc.isEmpty() || !esc.toLowerCase().equals("a")) {
					return;
				}
			}
		} while (true);
	}

	/**
	 * 部分匹配,返回第一个匹配的信息
	 * 
	 * @param regex
	 * @param matcherStr
	 * @return
	 */
	public static List<String> regexFind(String regex, String matcherStr) {
		List<String> returnList = null;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(matcherStr);
		while (matcher.find()) {
			returnList = new ArrayList<>();
			for (int i = 0; i <= matcher.groupCount(); i++) {
				returnList.add(matcher.group(i));
			}
			break;
		}
		return returnList;
	}

	public enum RequestMethod {
		GET, POST
	}

	public static String http_request(String requestUrl, RequestMethod requestMethod) {
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(8000);
			if (RequestMethod.POST == requestMethod) {
				connection.setDoOutput(true);
			}
			connection.setDoInput(true);
			connection.setRequestMethod(requestMethod.name());
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				sb.append(lines);
			}
			reader.close();
			// 断开连接
			connection.disconnect();
			return sb.toString();
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
		return null;
	}

}
