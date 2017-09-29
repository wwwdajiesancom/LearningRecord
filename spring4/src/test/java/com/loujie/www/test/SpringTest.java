package com.loujie.www.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.loujie.www.annocation.Person;
import com.loujie.www.helloword.HelloWorld;

public class SpringTest {

	private ClassPathXmlApplicationContext applicationContext;

	@Test
	public void s_helloworld() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:bean-cycle.xml");
		HelloWorld helloWorld = applicationContext.getBean(HelloWorld.class);
		helloWorld.hello();
		applicationContext.close();
	}

	@Test
	public void s_annocation() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:bean-annotion.xml");
		Person person = applicationContext.getBean(Person.class);
		System.err.println(person);
	}

	@Test
	public void mybatis() {

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(System.in);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.selectOne("");

	}

}
