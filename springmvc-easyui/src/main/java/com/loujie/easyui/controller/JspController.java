package com.loujie.easyui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/jsp")
public class JspController {

	private static final String dialogview = "/3/dialog/view";

	private static final String datagridAdd = "/4/menu/add";
	private static final String datagridUpdate = "/4/menu/update";
	
	@RequestMapping(value = "/dialog/view")
	public String dialogview(HttpServletRequest request, Model model) {

		return dialogview;
	}

	@RequestMapping(value="/datagrid/add")
	public String datagridAdd(HttpServletRequest request,Model model) {
		
		return datagridAdd;
	}
	
	@RequestMapping(value="/datagrid/update")
	public String datagridUpdate(HttpServletRequest request,Model model) {
		
		return datagridUpdate;
	}
	
}
