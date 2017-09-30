package com.loujie.easyui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	private static String index = "/index";

	/**
	 * 1.首页跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/index", "/" })
	public String index(HttpServletRequest request) {

		return index;
	}

}
