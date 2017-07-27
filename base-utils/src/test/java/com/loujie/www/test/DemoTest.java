package com.loujie.www.test;

import org.junit.Test;

import com.loujie.www.elastic.ElasticSearchUtils;
import com.loujie.www.solr.SolrJUtils;

public class DemoTest {

	@Test
	public void testl() {
		do {
			try {
				ElasticSearchUtils.example(1, 5);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (true);
	}

	@Test
	public void list() {
		do {
			try {
				SolrJUtils.example();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (true);
	}
}
