package com.loujie.easyui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/jsp")
public class JspController {

	private static final String dialogview = "/3/dialog/view";

	@RequestMapping(value = "/dialog/view")
	public String dialogview(HttpServletRequest request, Model model) {

		return dialogview;
	}

}
