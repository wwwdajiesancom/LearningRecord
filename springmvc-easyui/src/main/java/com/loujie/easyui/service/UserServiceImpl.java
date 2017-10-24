package com.loujie.easyui.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.loujie.easyui.dao.UserDao;
import com.loujie.easyui.entity.User;
import com.loujie.easyui.param.QueryUserParam;
import com.loujie.util.ArgsUtils;
import com.loujie.util.page.PageResult;

@Service
public class UserServiceImpl {

	@Resource
	private UserDao userDao;

	public PageResult findPage(QueryUserParam pageCon) {
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("ElikeName", pageCon.getName());
		conditionMap.put("id", pageCon.getId());
		
		Page<User> page = PageHelper.startPage(pageCon.getPageNum(), pageCon.getPageSize());

		userDao.getList(conditionMap);

		return new PageResult(page);

	}

	public void addUser(User addUser) {
		addUser.setPassword("123456789");
		userDao.save(addUser);
	}

	public void deleteUsers(Set<Integer> idSet) {
		if (!ArgsUtils.isEmpty(idSet)) {
			for (Integer id : idSet) {
				userDao.delete(id);
			}
		}
	}

	public void updateUser(User updateUser) {

		userDao.updateUserName(updateUser.getId(), updateUser.getName());
	}

	public User get(Integer id) {
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("id", id);
		return userDao.get(conditionMap);
	}

}
