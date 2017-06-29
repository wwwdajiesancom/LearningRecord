package com.loujie.www.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet(name = "myServlet", urlPatterns = {"/"})
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = -8028438757476008498L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "application/json;charset=UTF-8");
		Map<String, Object> map = new HashMap<>();
		map.put("name", "杰哥");
		map.put("code", "200");
		resp.getWriter().println(JSONObject.fromObject(map).toString());
	}

}
