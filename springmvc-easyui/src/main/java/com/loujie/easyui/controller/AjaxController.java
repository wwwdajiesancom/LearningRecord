package com.loujie.easyui.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loujie.easyui.entity.User;
import com.loujie.easyui.param.QueryUserParam;
import com.loujie.easyui.service.UserServiceImpl;
import com.loujie.util.ArgsUtils;
import com.loujie.util.page.PageResult;
import com.loujie.util.result.ResultDto;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/findpage")
	@ResponseBody
	public ResultDto findpage(HttpServletRequest request, QueryUserParam param) {
		ResultDto resultDto = new ResultDto();
		PageResult pageResult = userServiceImpl.findPage(param);
		resultDto.initPage(pageResult);
		return resultDto;
	}

	@RequestMapping("/datagrid/add")
	@ResponseBody
	public ResultDto add(User user) {
		ResultDto resultDto = new ResultDto();
		userServiceImpl.addUser(user);
		return resultDto;
	}

	@RequestMapping("/datagrid/update")
	@ResponseBody
	public ResultDto update(User user) {
		ResultDto resultDto = new ResultDto();
		userServiceImpl.updateUser(user);
		return resultDto;
	}

	@RequestMapping("/datagrid/delete/{id}")
	@ResponseBody
	public ResultDto update(@PathVariable(name = "id") String ids) {
		ResultDto resultDto = new ResultDto();
		Set<Integer> idSet = new HashSet<>();
		String[] idStrings = ids.split(",");
		for (String id : idStrings) {
			if (!ArgsUtils.isEmpty(id)) {
				idSet.add(ArgsUtils.parseInteger(id, -1));
			}
		}

		userServiceImpl.deleteUsers(idSet);
		return resultDto;
	}

}
