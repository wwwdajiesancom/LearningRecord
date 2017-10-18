package com.loujie.easyui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loujie.easyui.param.QueryCityParam;
import com.loujie.easyui.service.CityServiceImpl;
import com.loujie.util.page.PageResult;
import com.loujie.util.result.ResultDto;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

	@Autowired
	private CityServiceImpl cityServiceImpl;

	@RequestMapping(value = "/dialog/view")
	@ResponseBody
	public ResultDto dialogview(HttpServletRequest request) {

		System.err.println(request.getParameter("name"));
		System.err.println(request.getParameter("email"));

		ResultDto resultDto = new ResultDto();

		return resultDto;

	}

	@RequestMapping(value = "/datagrid/add")
	@ResponseBody
	public ResultDto datagridAdd(HttpServletRequest request) {

		System.err.println(this.getClass().getName() + ".datagridAdd");

		ResultDto resultDto = new ResultDto();

		return resultDto;

	}

	@RequestMapping(value = "/datagrid/update")
	@ResponseBody
	public ResultDto datagridUpdate(HttpServletRequest request) {

		System.err.println(this.getClass().getName() + ".datagridUpdate");

		ResultDto resultDto = new ResultDto();

		return resultDto;

	}

	@RequestMapping(value = "/datagrid/delete")
	@ResponseBody
	public ResultDto datagridDelete(HttpServletRequest request, String ids) {

		System.err.println(ids);
		System.err.println(this.getClass().getName() + ".datagridDelete");

		ResultDto resultDto = new ResultDto();

		return resultDto;

	}

	@RequestMapping("/findpage")
	@ResponseBody
	public ResultDto findpage(HttpServletRequest request, QueryCityParam param) {

		ResultDto resultDto = new ResultDto();

		PageResult pageResult = cityServiceImpl.findPage(param);

		resultDto.initPage(pageResult);

		return resultDto;

	}

}
