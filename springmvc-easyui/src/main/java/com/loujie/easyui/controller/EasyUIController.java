package com.loujie.easyui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/easyui")
public class EasyUIController {

	@RequestMapping(value = "/1/{path}/{easyuiModel}")
	public String oneStudy(@PathVariable String path, @PathVariable String easyuiModel) {

		return "/1/" + path + "/" + easyuiModel;
	}

}
