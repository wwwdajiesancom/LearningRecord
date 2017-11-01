package com.loujie.easyui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.loujie.easyui.entity.User;
import com.loujie.easyui.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/jsp")
public class JspController {

	private static final String datagridView = "/4/datagrid/view";
	private static final String datagridAdd = "/4/datagrid/add";
	private static final String datagridUpdate = "/4/datagrid/update";

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/datagrid/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Integer id, Model model) {
		User user = userServiceImpl.get(id);
		model.addAttribute("user", user);
		return datagridView;
	}

	@RequestMapping(value = "/datagrid/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Mode mode) {
		System.out.println("abc---------------------------");
		return datagridAdd;
	}

	@RequestMapping(value = "/datagrid/update/{id}")
	public String update(@PathVariable Integer id, Model model) {
		System.err.println("update:" + id);
		User user = userServiceImpl.get(id);
		model.addAttribute("user", user);
		return datagridUpdate;
	}

}
