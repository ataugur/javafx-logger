package com.datayaz.util;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

	private Utils() { // to prevent public instantiation
	}
	
	public static InputStream getConfResource(String propertyFile) {
		return Utils.class.getClassLoader().getResourceAsStream("conf/" + propertyFile);
	}

}
