package com.loujie.www.test;

import java.util.Date;

import org.junit.Test;

import com.loujie.www.util.ArgsUtils;

public class SolrTest {

	@Test
	public void solr1() {
		System.err.println(ArgsUtils.formatDate(new Date(1505874660000L), "yyyy-MM-dd HH:mm:ss"));
	}

}
