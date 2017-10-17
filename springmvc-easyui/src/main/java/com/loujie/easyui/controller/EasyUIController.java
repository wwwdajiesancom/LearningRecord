package com.loujie.easyui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/easyui")
public class EasyUIController {

	/**
	 * 1easyui基组件,自己是独立的 2easyui布局组件 3easyui-windows窗体4easyui-menu.button
	 * 
	 * @param index
	 * @param path
	 * @param easyuiModel
	 * @return
	 */
	@RequestMapping(value = "/{index}/{path}/{easyuiModel}")
	public String twoStudy(@PathVariable String index, @PathVariable String path, @PathVariable String easyuiModel) {

		return "/" + index + "/" + path + "/" + easyuiModel;
	}

}