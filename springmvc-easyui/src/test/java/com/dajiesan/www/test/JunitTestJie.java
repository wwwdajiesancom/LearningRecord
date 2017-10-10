package com.dajiesan.www.test;

import java.util.Date;

import org.junit.Test;

import com.loujie.util.ArgsUtils;

public class JunitTestJie {

	@Test
	public void date_test() {
		long date = 1507514700000l;
		String result = ArgsUtils.formatDate(new Date(date), "yyyy-MM-dd HH:mm:ss");
		System.out.println(result);
	}

}
