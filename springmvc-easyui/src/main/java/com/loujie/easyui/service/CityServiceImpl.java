package com.loujie.easyui.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.loujie.easyui.dao.CityDao;
import com.loujie.easyui.entity.City;
import com.loujie.easyui.param.QueryCityParam;
import com.loujie.util.ArgsUtils;
import com.loujie.util.page.PageResult;

@Service
public class CityServiceImpl {

	@Resource
	private CityDao cityDao;

	public PageResult findPage(QueryCityParam param) {

		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("ElikeName", param.getName());
		conditionMap.put("id", param.getId());

		Page<Object> page = PageHelper.startPage(param.getPageNum(), param.getPageSize());

		cityDao.findList(conditionMap);

		return new PageResult(page);
	}

	public City findOne(Integer id, String name) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("id", id);
		City city = cityDao.findOne(conditionMap);

		return city;
	}

	public boolean updateCity(City city) {
		cityDao.updateCity(city);

		return true;
	}

	public boolean delete(Set<Integer> ids) {
		if (!ArgsUtils.isEmpty(ids)) {
			for (Integer id : ids) {
				if (!ArgsUtils.isEmpty(id)) {
					cityDao.delete(id);
				}
			}
		}
		return true;
	}

	public boolean save(City city) {
		cityDao.save(city);
		return true;
	}

}
