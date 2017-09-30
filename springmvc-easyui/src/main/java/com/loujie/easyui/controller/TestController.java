package com.loujie.easyui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loujie.easyui.entity.City;
import com.loujie.easyui.service.CityServiceImpl;
import com.loujie.util.page.PageCon;
import com.loujie.util.page.PageResult;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private CityServiceImpl cityServiceImpl;

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object List(PageCon pageCon) {
		try {
			PageResult pageResult = cityServiceImpl.findPage(pageCon);
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return "系统错误";
		}
	}

	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Object test(@PathVariable Integer id, String name) {
		try {
			City city = cityServiceImpl.findOne(id, name);
			return city;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/update/{id}")
	@ResponseBody
	public Object update(@PathVariable Integer id, String name) {
		try {
			City city = new City();
			city.setId(id);
			if (name == null || name.isEmpty()) {
				city.setName("jiege");
			} else {
				city.setName(name);
			}
			return cityServiceImpl.updateCity(city);
		} catch (Exception e) {
			return e.getMessage();
		}

	}

}
