package com.loujie.easyui.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loujie.easyui.dao.base.BaseDao;
import com.loujie.easyui.entity.User;

@Repository
public interface UserDao extends BaseDao<User> {

	int save(User user);

	int updateUserName(@Param("id") Integer id, @Param("name") String name);

	int delete(@Param("id") Integer id);

}
