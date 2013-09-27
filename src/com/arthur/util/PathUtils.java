package com.arthur.util;

import java.io.File;

public class PathUtils {
	
	private static String rootPath;
	
	private static final String CONF_PATH = File.separator + "conf" + File.separator;
	
	static {
		rootPath = new File("").getAbsolutePath();
	}

	public static String getRootPath() {
		return rootPath;
	}
	
	public static String getConfPath() {
		return getRootPath() + CONF_PATH;
	}
}
