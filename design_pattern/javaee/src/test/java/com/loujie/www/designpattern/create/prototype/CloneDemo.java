package com.loujie.www.designpattern.create.prototype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * 原型模式测试
 * 
 * @author loujie
 *
 */
public class CloneDemo {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("deprecation")
	@Test
	public void shallowTest() throws ParseException, CloneNotSupportedException {
		Date birthday = sdf.parse("2017-06-02 15:12:12");
		Sheep s1 = new Sheep("nx", birthday);
		Sheep s2 = (Sheep) s1.clone();
		System.err.println("s1:" + s1);
		System.err.println("s1.birthday:" + sdf.format(s1.getBirthday()));
		System.err.println("s2:" + s2);
		birthday.setMinutes(23);
		System.err.println("s2.birthday:" + sdf.format(s2.getBirthday()));
	}

	@Test
	public void deepTest() {

	}

}
