package com.loujie.easyui.dao.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	
	T get(Map<String, Object> conditionMap);
	
	List<T> getList(Map<String, Object> conditionMap);

}
