package com.loujie.easyui.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.loujie.easyui.dao.CityDao;
import com.loujie.easyui.entity.City;
import com.loujie.util.page.PageCon;
import com.loujie.util.page.PageResult;

@Service
public class CityServiceImpl {

	@Resource
	private CityDao cityDao;

	public PageResult findPage(PageCon pageCon) {

		Map<String, Object> conditionMap = new HashMap<>();

		Page<Object> page = PageHelper.startPage(pageCon.getPageNum(), pageCon.getPageSize());

		cityDao.findList(conditionMap);

		return new PageResult(page);
	}

	public City findOne(Integer id, String name) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("id", id);
		City city = cityDao.findOne(conditionMap);
		if ("jiege".equals(city.getName())) {
			city.setName(name);
			cityDao.updateCity(city);
		}
		if ("error".equals(name)) {
			throw new RuntimeException("name is not error");
		}
		return city;
	}

	public int updateCity(City city) {
		int result = cityDao.updateCity(city);
		if ("error".equals(city.getName())) {
			throw new RuntimeException("name is not error");
		}
		return result;
	}

}
