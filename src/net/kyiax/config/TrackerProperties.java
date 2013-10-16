package net.kyiax.config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.kyiax.util.PathUtils;
import net.kyiax.util.PathUtils;

public class TrackerProperties {

	private static String fileName = "gatracker.properties";
	
	public static String gaEnable = "1";
	public static String gaTrackingCode = "UA-43779467-1";
	public static String gaPageTitle = "Event";
	public static String gaHostName = "game.arthurcn.com";
	
	static {
		String filePath = PathUtils.getConfPath() + File.separator + fileName;
		
	    // Load properties
	    Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(filePath));
			props.load(in);
			
			gaEnable = props.getProperty("ga.enable", gaEnable);
			gaTrackingCode = props.getProperty("ga.tracking.code", gaTrackingCode);
			gaPageTitle = props.getProperty("ga.page.title", gaPageTitle);
			gaHostName = props.getProperty("ga.host.name", gaHostName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
