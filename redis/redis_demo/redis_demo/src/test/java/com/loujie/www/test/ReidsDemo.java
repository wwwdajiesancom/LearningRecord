package com.loujie.www.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReidsDemo {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void nxf() throws InterruptedException {
		for (long i = 0; i < 100000l * 100000l; i++) {
			logger.debug(i
					+ " There are moments in life when you miss someone so much that you just want to pick them from your dreams and hug them for real! Dream what you want to dream;go where you want to go;be what you want to be,because you have only one life and one chance to do all the things you want to do.");
		}
	}

}
