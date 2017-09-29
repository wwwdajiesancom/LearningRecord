package com.loujie.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loujie.springmvc.entity.City;
import com.loujie.springmvc.service.CityServiceImpl;

@Controller
@RequestMapping(value = "/springmvc")
public class SpringMvcController {

	@Autowired
	private CityServiceImpl cityServiceImpl;

	@ResponseBody
	@RequestMapping(value = "/test/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public Object test(HttpServletRequest request, @PathVariable Integer id) {
		City city = cityServiceImpl.findOne(id);
		return city;
	}

	@RequestMapping(value = "/update/{id}", method = {})
	public Object testUpdate(HttpServletRequest request, @PathVariable Integer id) {
		City updateCity = new City();
		updateCity.setId(id);
		updateCity.setName("henghenghaha");
		int result = cityServiceImpl.updateCity(updateCity);
		return result;
	}

}
