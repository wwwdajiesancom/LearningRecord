package com.loujie.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloWorldController implements Controller {

	Logger _logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		_logger.info("访问了ben方法");
		Map<String, Object> itemMap = new HashMap<>();
		itemMap.put("name", "jiege2017");
		itemMap.put("age", 28);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemMap", itemMap);
		modelAndView.setViewName("/WEB-INF/jsp/helloworld.jsp");
		return modelAndView;
	}

}
