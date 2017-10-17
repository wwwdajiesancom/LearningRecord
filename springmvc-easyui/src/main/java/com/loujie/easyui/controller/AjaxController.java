package com.loujie.easyui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loujie.util.result.ResultDto;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

	@RequestMapping(value = "/dialog/view")
	@ResponseBody
	public ResultDto dialogview(HttpServletRequest request) {

		System.err.println(request.getParameter("name"));
		System.err.println(request.getParameter("email"));

		ResultDto resultDto = new ResultDto();

		return resultDto;

	}

}
