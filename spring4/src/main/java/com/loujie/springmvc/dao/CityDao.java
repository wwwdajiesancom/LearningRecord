package com.loujie.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.loujie.springmvc.entity.City;

@Repository
public interface CityDao {

	City findOne(Map<String, Object> conditionMap);

	List<City> findList(Map<String, Object> conditionMap);

	int updateCity(City city);

}
