package com.arthurcn;

import com.arthurcn.analytic.BriefOperate;
import com.arthurcn.analytic.DistrGoldConsume;
import com.arthurcn.config.TrackerProperties;

import java.util.List;
import java.util.Map;

public class GATracker {

	// Page title
	private String pageTitle;
	// Host name
	private String hostName;

	/**
	 * Google analytic
	 */
	public GATracker() {

	}

	/**
	 * Full constructor with page title and host name
	 *
	 * @param pageTitle always means the server name
	 * @param hostName always means the server url
	 */
	public GATracker(String pageTitle, String hostName) {
		this.pageTitle = pageTitle;
		this.hostName = hostName;
	}

	/**
	 * Track operation brief report
	 *
	 * @param briefOperate Operation brief data
	 */
	public void trackBriefOperate(BriefOperate briefOperate) {
		String eventCategory = Events.EC_OPERATION;
		String eventAction = Events.EA_LOG;

		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_PLAYER_CREATE_COUNT, briefOperate.getPlayerCreateCount(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_TOTAL_REGISTER_COUNT, briefOperate.getTotalRegisterCount(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_REGISTER_CONVERSION, briefOperate.getRegisterConversion(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ENTER_GAME_PLAYER_COUNT, briefOperate.getEnterGamePlayerCount(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_LOGIN_ACCOUNT_COUNT, briefOperate.getLoginAccountCount(), pageTitle, hostName);

		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ONLINE_AVG_COUNT, briefOperate.getOnlineAvgCount(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ONLINE_MAX_COUNT, briefOperate.getOnlineMaxCount(), pageTitle, hostName);

		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_TOTAL_CHARGE_MONEY, briefOperate.getTotalChargeMoney(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_TOTAL_CHARGE_GOLD, briefOperate.getTotalChargeGold(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_CHARGE_ACCOUNT_COUNT, briefOperate.getChargeAccountCount(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_CHARGE_COUNT, briefOperate.getChargeCount(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ARPU, briefOperate.getArpu(), pageTitle, hostName);
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ACTIVE_PAY_PERMEABILITY, briefOperate.getActivePayPermeability(), pageTitle, hostName);
	}

	/**
	 * Track level distribution
	 * <p/>
	 * Distribution contains the all the level, from 1 ~ maxLevel
	 *
	 * @param maxLevel          Max level
	 * @param levelDistribution Level distribution map, key is level, value is player number
	 */
	public void trackDistrLevel(int maxLevel, Map<Integer, Integer> levelDistribution) {
		String eventCategory = Events.EC_LEVEL_DIST;
		String eventAction = Events.EA_PLAYER_NUMBER;

		for (int i = 1; i <= maxLevel; i++) {
			int playerNumber = 0;
			if (levelDistribution.containsKey(i)) {
				playerNumber = levelDistribution.get(i);
			}

			EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_LEVEL + " " + i, playerNumber, pageTitle, hostName);
		}
	}

	/**
	 * Track gold consume distribution
	 *
	 * @param goldConsumeDistribution Gold consume distribution, specific object list
	 */
	public void trackDistrGoldConsume(List<DistrGoldConsume> goldConsumeDistribution) {
		String eventCategory = Events.EC_GOLD_CONSUME;

		for (int i = 0; i < goldConsumeDistribution.size(); i++) {
			DistrGoldConsume distrGoldConsume = goldConsumeDistribution.get(i);
			if (distrGoldConsume != null) {
				EventQueue.INSTANCE.push(eventCategory, Events.EA_CONSUME_VALUE, Events.EL_GOLD_CONSUME_TYPE + " " + distrGoldConsume.getType(), distrGoldConsume.getConsumeValue(), pageTitle, hostName);
				EventQueue.INSTANCE.push(eventCategory, Events.EA_CONSUME_PLAYERCOUNT, Events.EL_GOLD_CONSUME_TYPE + " " + distrGoldConsume.getType(), distrGoldConsume.getConsumePlayerCount(), pageTitle, hostName);
				EventQueue.INSTANCE.push(eventCategory, Events.EA_CONSUME_COUNT, Events.EL_GOLD_CONSUME_TYPE + " " + distrGoldConsume.getType(), distrGoldConsume.getConsumeCount(), pageTitle, hostName);
			}
		}
	}
}
