package com.loujie.www.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loujie.www.redis.RedisUtils;
import com.loujie.www.util.ConvertUtils;

/**
 * 获取key的方法
 * 
 * @author loujie
 *
 */
@WebServlet(urlPatterns = "/key", loadOnStartup = 1)
public class KeyServlet extends HttpServlet {

	private static final long serialVersionUID = 5698328582081031481L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.找到key
		String key = req.getParameter("key");
		if (key == null || key.isEmpty() || key.length() != 32 || key.contains("?") || key.contains("*")) {
			resp.getWriter().print("key is error");
			return;
		}
		// 2.验证key,并返回值
		String result = RedisUtils.getM3u8KeyValue(key);
		if (result == null || result.isEmpty()) {
			resp.getWriter().print("key not exists");
			return;
		}
		System.err.println("key:" + key + ",result:" + result);
		// 3.返回16进制字符串
		byte[] bytes = ConvertUtils.hexStringToBytes(result);
		resp.reset();
		resp.setContentType("application/octet-stream");
		resp.setContentLength(bytes.length);
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		bos.write(bytes);
		bos.flush();
		bos.close();
	}

}
