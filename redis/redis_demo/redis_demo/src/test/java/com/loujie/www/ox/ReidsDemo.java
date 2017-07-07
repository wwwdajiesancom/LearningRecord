package com.loujie.www.ox;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ReidsDemo {

	private Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void nxf() {
		for (int i = 0; i < 100000; i++) {
			logger.debug(i + " There are mome");
		}
	}

}
