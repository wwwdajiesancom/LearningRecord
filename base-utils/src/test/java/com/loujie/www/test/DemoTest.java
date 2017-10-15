package com.loujie.www.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.loujie.www.http.HttpUtils;

public class DemoTest {

	@Test
	public void regex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("apikey", "2b684ae4911e9ab9369b7dd4ebe33dce");
		String result = HttpUtils.doHttpPost("http://api2.pbsedu.com/pbslive/soon_live_courseware", params);
		System.out.println(result);
	}

	@Test
	public void listCompare() {
		System.err.println(this.getClass().getName()+"");
	}

}
