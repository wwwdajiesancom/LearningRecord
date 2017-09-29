package com.loujie.easyui.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.loujie.easyui.dao.CityDao;
import com.loujie.easyui.entity.City;

@Service
public class CityServiceImpl {

	@Resource
	private CityDao cityDao;

	public City findOne(Integer id) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("id", id);
		City city = cityDao.findOne(conditionMap);
		return city;
	}

	public int updateCity(City city) {
		int result = cityDao.updateCity(city);
		return result;
	}

}
