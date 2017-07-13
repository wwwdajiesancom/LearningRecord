package com.loujie.www.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "myServlet", urlPatterns = {"/", "/index", "/admin"})
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = -8028438757476008498L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/index.html").forward(req, resp);
	}

}
