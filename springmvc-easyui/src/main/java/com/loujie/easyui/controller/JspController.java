package com.loujie.easyui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.loujie.easyui.entity.City;
import com.loujie.easyui.service.CityServiceImpl;

@Controller
@RequestMapping(value = "/jsp")
public class JspController {

	private static final String dialogview = "/3/dialog/view";

	private static final String datagridView = "/4/menu/view";
	private static final String datagridAdd = "/4/menu/add";
	private static final String datagridUpdate = "/4/menu/update";

	@Autowired
	private CityServiceImpl cityServiceImpl;

	@RequestMapping(value = "/dialog/view")
	public String dialogview(HttpServletRequest request, Integer id, Model model) {

		return dialogview;
	}

	@RequestMapping(value = "/datagrid/view", method = RequestMethod.GET)
	public String datagridView(HttpServletRequest request, Integer id, Model model) {
		City city = cityServiceImpl.findOne(id, null);
		model.addAttribute("city", city);
		return datagridView;
	}

	@RequestMapping(value = "/datagrid/add", method = RequestMethod.GET)
	public String datagridAdd(HttpServletRequest request, Model model) {

		return datagridAdd;
	}

	@RequestMapping(value = "/datagrid/update", method = RequestMethod.GET)
	public String datagridUpdate(HttpServletRequest request, Integer id, Model model) {
		City city = cityServiceImpl.findOne(id, null);
		model.addAttribute("city", city);
		return datagridUpdate;
	}

}
