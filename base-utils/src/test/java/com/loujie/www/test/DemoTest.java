package com.loujie.www.test;

<<<<<<< HEAD
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
=======
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.loujie.www.elastic.ElasticSearchUtils;

public class DemoTest {
	@Test
	public void testl() {
		ElasticSearchUtils.example(1, 5);
>>>>>>> 4a906f9cb53f27b3e7a713782f22bb342e7dfe0e
	}

	@Test
	public void list() {
<<<<<<< HEAD
		do {
			try {
				SolrJUtils.example();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (true);
=======
		List<String> list = new ArrayList<>();
		list.add("redis/redis_demo/redis_demo/.classpath");
		list.add("redis/redis_demo/redis_demo/.gitignore");
		list.add("redis/redis_demo/redis_demo/.project");
		list.add("redis/redis_demo/redis_demo/.settings/.jsdtscope");
		list.add("redis/redis_demo/redis_demo/.settings/org.eclipse.core.resources.prefs");
		list.add("redis/redis_demo/redis_demo/.settings/org.eclipse.jdt.core.prefs");
		list.add("redis/redis_demo/redis_demo/.settings/org.eclipse.m2e.core.prefs");
		list.add("redis/redis_demo/redis_demo/.settings/org.eclipse.wst.common.component");
		list.add("redis/redis_demo/redis_demo/.settings/org.eclipse.wst.common.project.facet.core.xml");
		list.add("redis/redis_demo/redis_demo/.settings/org.eclipse.wst.jsdt.ui.superType.container");
		list.add("redis/redis_demo/redis_demo/.settings/org.eclipse.wst.jsdt.ui.superType.name");
		list.add("redis/redis_demo/redis_demo/.settings/org.eclipse.wst.validation.prefs");
		list.add("redis/redis_demo/redis_demo/business-2017-06-29.log");
		list.add("redis/redis_demo/redis_demo/file.log");
		list.add("redis/redis_demo/redis_demo/pom.xml");
		list.add("redis/redis_demo/redis_demo/src/main/filters/filter-deploy.properties");
		list.add("redis/redis_demo/redis_demo/src/main/filters/filter-dev.properties");
		list.add("redis/redis_demo/redis_demo/src/main/java/com/loujie/www/redis/RedisUtils.java");
		list.add("redis/redis_demo/redis_demo/src/main/java/com/loujie/www/servlet/MyServlet.java");
		list.add("redis/redis_demo/redis_demo/src/main/java/com/loujie/www/utils/ArgsUitls.java");
		list.add("redis/redis_demo/redis_demo/src/main/resources/config.properties");
		list.add("redis/redis_demo/redis_demo/src/main/resources/log4j.properties");
		list.add("redis/redis_demo/redis_demo/src/main/resources/logback.xml");
		list.add("redis/redis_demo/redis_demo/src/main/webapp/WEB-INF/web.xml");
		list.add("redis/redis_demo/redis_demo/src/test/java/com/loujie/www/ox/ReidsDemo.java");
		list.add("redis/redis_demo/redis_demo/src/test/java/com/loujie/www/solr/SolrTest.java");
		list.add("redis/redis_demo/redis_demo/src/test/java/com/loujie/www/test/ReidsDemo.java");
		String li = "";
		for (String item : list) {
			li += (item + " ");
		}
		System.err.println(li);

>>>>>>> 4a906f9cb53f27b3e7a713782f22bb342e7dfe0e
	}
}
