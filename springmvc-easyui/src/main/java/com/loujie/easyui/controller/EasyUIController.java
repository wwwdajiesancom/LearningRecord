package com.loujie.easyui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/easyui")
public class EasyUIController {

	/**
	 * 1.easyui基组件,自己是独立的
	 * 
	 * @param path
	 * @param easyuiModel
	 * @return
	 */
	@RequestMapping(value = "/1/{path}/{easyuiModel}")
	public String oneStudy(@PathVariable String path, @PathVariable String easyuiModel) {

		return "/1/" + path + "/" + easyuiModel;
	}

	/**
	 * 2.easyui布局组件
	 * 
	 * @param path
	 * @param easyuiModel
	 * @return
	 */
	@RequestMapping(value = "/2/{path}/{easyuiModel}")
	public String twoStudy(@PathVariable String path, @PathVariable String easyuiModel) {

		return "/2/" + path + "/" + easyuiModel;
	}

}
