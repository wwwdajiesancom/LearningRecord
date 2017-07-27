package com.loujie.www.http.useragent;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 娄杰
 * 
 * @author loujie
 *
 */
public class UserAgentUtils {

	/**
	 * 获取用户代理对象
	 * 
	 * @param request
	 * @return
	 */
	public static UserAgent getUserAgent(HttpServletRequest request) {
		return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	}

	/**
	 * 获取设备类型
	 * 
	 * @param request
	 * @return
	 */
	public static DeviceType getDeviceType(HttpServletRequest request) {
		if (request.getSession().getAttribute("deviceType") != null) {
			return (DeviceType) request.getSession().getAttribute("deviceType");
		}
		return getUserAgent(request).getOperatingSystem().getDeviceType();
	}

	/**
	 * 是否是PC
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isComputer(HttpServletRequest request) {
		return DeviceType.COMPUTER.equals(getDeviceType(request));
	}

	/**
	 * 是否是手机
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMobile(HttpServletRequest request) {
		return DeviceType.MOBILE.equals(getDeviceType(request));
	}

	/**
	 * 是否是平板
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isTablet(HttpServletRequest request) {
		return DeviceType.TABLET.equals(getDeviceType(request));
	}

	/**
	 * 是否是手机和平板
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMobileOrTablet(HttpServletRequest request) {
		DeviceType deviceType = getDeviceType(request);
		return DeviceType.MOBILE.equals(deviceType) || DeviceType.TABLET.equals(deviceType);
	}

	/**
	 * 是否是电视端
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isTv(HttpServletRequest request) {
		return DeviceType.DMR.equals(getDeviceType(request));
	}

	/**
	 * 根据不同的访问设备,返回不同的地址;目前我们分为[pc端=默认],[移动端=_mobile],[电视端=_tv]
	 * 
	 * @param urlMap
	 *            返回的Url地址集合
	 * @param key
	 *            返回地址键值名称,如果找不到就默认为pc
	 * @return
	 */
	public static String getReturnUrl(HttpServletRequest request, Map<String, String> urlMap, String key) {
		DeviceType deviceType = getDeviceType(request);
		switch (deviceType) {
			case COMPUTER// 台式机或笔记本
			:
				return urlMap.get(key);
			case MOBILE// 移动设备
			:
			case TABLET// 平板
			:
			case DMR// 电视
			:
			case WEARABLE :// 智能手表等
			case GAME_CONSOLE// 游戏机
			:
				return urlMap.get(key + "_mobile");
			default :
				return urlMap.get(key);
		}
	}

}
