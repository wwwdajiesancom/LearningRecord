package com.loujie.www.test;

import java.util.Date;

import org.junit.Test;

import com.loujie.www.util.ArgsUtils;

public class SolrTest {

	@Test
	public void solr1() {
		System.err.println(ArgsUtils.formatDate(new Date(1505727000000l), "yyyy-MM-dd HH:mm:ss"));
		System.err.println(ArgsUtils.parseDate("2017-09-18 11:06:33", "yyyy-MM-dd HH:mm:ss").getTime());
	}

}
