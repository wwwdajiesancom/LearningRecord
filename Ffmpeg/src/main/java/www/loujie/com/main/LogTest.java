package www.loujie.com.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

	static Logger logger = LoggerFactory.getLogger(LogTest.class);

	public static void main(String[] args) {
		logger.debug("debug--------start");
		logger.info("info----------start");
		logger.warn("warn-----------start");
		logger.error("error--------start");
	}

}
