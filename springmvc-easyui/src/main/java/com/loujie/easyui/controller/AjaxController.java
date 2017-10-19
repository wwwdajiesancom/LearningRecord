package com.loujie.easyui.controller;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loujie.easyui.entity.City;
import com.loujie.easyui.param.QueryCityParam;
import com.loujie.easyui.service.CityServiceImpl;
import com.loujie.util.ArgsUtils;
import com.loujie.util.page.PageResult;
import com.loujie.util.result.ResultDto;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

	@Autowired
	private CityServiceImpl cityServiceImpl;

	@RequestMapping("/findpage")
	@ResponseBody
	public ResultDto findpage(HttpServletRequest request, QueryCityParam param) {
		ResultDto resultDto = new ResultDto();
		PageResult pageResult = cityServiceImpl.findPage(param);
		resultDto.initPage(pageResult);
		return resultDto;
	}

	@RequestMapping("/datagrid/add")
	@ResponseBody
	public ResultDto add(HttpServletRequest request, City city) {
		ResultDto resultDto = new ResultDto();
		
		city.setCreatedAt(Calendar.getInstance().getTime());
		city.setUpdatedAt(Calendar.getInstance().getTime());
		cityServiceImpl.save(city);

		return resultDto;
	}

	@RequestMapping("/datagrid/update")
	@ResponseBody
	public ResultDto update(HttpServletRequest request, City city) {
		ResultDto resultDto = new ResultDto();

		cityServiceImpl.updateCity(city);

		return resultDto;
	}

	@RequestMapping("/datagrid/delete")
	@ResponseBody
	public ResultDto delete(HttpServletRequest request, String id) {
		ResultDto resultDto = new ResultDto();
		Set<Integer> idSet = new HashSet<>();
		if (!ArgsUtils.isEmpty(id)) {
			for (String item : id.split(",")) {
				if (!ArgsUtils.isEmpty(item)) {
					idSet.add(ArgsUtils.parseInteger(item, -1));
				}
			}
		}
		cityServiceImpl.delete(idSet);
		return resultDto;
	}

}
