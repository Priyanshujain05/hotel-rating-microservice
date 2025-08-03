package com.user.service.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

//	public Logger logger = LoggerFactory.getLogger("UserLogger");

	public static Logger getLogger(String name) {
		return LoggerFactory.getLogger(name);
	}

}
