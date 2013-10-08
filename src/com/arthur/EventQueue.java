package com.arthur;

import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arthur.config.TrackerProperties;
import com.dmurph.tracking.AnalyticsConfigData;
import com.dmurph.tracking.AnalyticsRequestData;
import com.dmurph.tracking.JGoogleAnalyticsTracker;
import com.dmurph.tracking.JGoogleAnalyticsTracker.GoogleAnalyticsVersion;

public enum EventQueue {
	
	INSTANCE;
	
	private static final Logger LOG = LoggerFactory.getLogger(EventQueue.class);
		
	private AnalyticsConfigData config;
	private JGoogleAnalyticsTracker tracker;
	
	private Vector<AnalyticsRequestData> vector = new Vector<AnalyticsRequestData>();
	private ScheduledExecutorService  scheduledExec = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * Initialization
	 */
	public void init() {
		config = new AnalyticsConfigData(TrackerProperties.gaTrackingCode);
		tracker = new JGoogleAnalyticsTracker(config, GoogleAnalyticsVersion.V_4_7_2);
	}
	
	/**
	 * Start
	 */
	public void start() {
		int enable = 1;
		try {
			enable = Integer.parseInt(TrackerProperties.gaEnable);
		} catch (NumberFormatException e) {
			// Ignore
		}
		
		if (enable == 1) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						if (!vector.isEmpty()) {
							AnalyticsRequestData argData = vector.remove(0);
							tracker.makeCustomRequest(argData);
							
							LOG.debug("Make custom request to google analytic. {}", argData);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			
			scheduledExec.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		} else {
			tracker.setEnabled(false);
		}
	}
	
	/**
	 * Stop
	 */
	public void stop() {
		scheduledExec.shutdown();
	}
	
	/**
	 * Push event with default page title and host name
	 * 
	 * @param eventCategory
	 * @param eventAction
	 * @param eventLabel
	 * @param eventValue
	 */
	public void push(String eventCategory, String eventAction, String eventLabel, int eventValue) {
		push(eventCategory, eventAction, eventLabel, eventValue, TrackerProperties.gaPageTitle, TrackerProperties.gaHostName);
	}
	
	/**
	 * Push event 
	 * 
	 * @param eventCategory
	 * @param eventAction
	 * @param eventLabel
	 * @param eventValue
	 * @param pageTitle
	 * @param hostName
	 */
	public void push(String eventCategory, String eventAction, String eventLabel, int eventValue, String pageTitle, String hostName) {
		AnalyticsRequestData data = new AnalyticsRequestData();
		data.setEventCategory(eventCategory);
		data.setEventAction(eventAction);
		data.setEventLabel(eventLabel);
		data.setEventValue(eventValue);
		data.setPageTitle(pageTitle);
		data.setHostName(hostName);
		
		vector.add(data);
	}
}
