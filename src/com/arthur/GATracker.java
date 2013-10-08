package com.arthur;

import java.util.List;
import java.util.Map;

import com.arthur.analytic.BriefOperate;
import com.arthur.analytic.DistrGoldConsume;

public class GATracker {

	/**
	 * Google analytic
	 */
	public GATracker() {
		
	}
	
	/**
	 * Track operation brief report
	 * 
	 * @param briefOperate Operation brief data
	 */
	public void trackBriefOperate(BriefOperate briefOperate) {
		String eventCategory = Events.EC_OPERATION;
		String eventAction = Events.EA_LOG;
		
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_PLAYER_CREATE_COUNT, briefOperate.getPlayerCreateCount());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_TOTAL_REGISTER_COUNT, briefOperate.getTotalRegisterCount());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_REGISTER_CONVERSION, briefOperate.getRegisterConversion());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ENTER_GAME_PLAYER_COUNT, briefOperate.getEnterGamePlayerCount());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_LOGIN_ACCOUNT_COUNT, briefOperate.getLoginAccountCount());
		
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ONLINE_AVG_COUNT, briefOperate.getOnlineAvgCount());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ONLINE_MAX_COUNT, briefOperate.getOnlineMaxCount());
		
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_TOTAL_CHARGE_MONEY, briefOperate.getTotalChargeMoney());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_TOTAL_CHARGE_GOLD, briefOperate.getTotalChargeGold());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_CHARGE_ACCOUNT_COUNT, briefOperate.getChargeAccountCount());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_CHARGE_COUNT, briefOperate.getChargeCount());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ARPU, briefOperate.getArpu());
		EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_ACTIVE_PAY_PERMEABILITY, briefOperate.getActivePayPermeability());
	}
	
	/**
	 * Track level distribution
	 * 
	 * Distribution contains the all the level, from 1 ~ maxLevel
	 * 
	 * @param maxLevel Max level
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
			
			EventQueue.INSTANCE.push(eventCategory, eventAction, Events.EL_LEVEL + " " + i, playerNumber);
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
				EventQueue.INSTANCE.push(eventCategory, Events.EA_CONSUME_VALUE, Events.EL_GOLD_CONSUME_TYPE + " " + i, distrGoldConsume.getConsumeValue());
				EventQueue.INSTANCE.push(eventCategory, Events.EA_CONSUME_PLAYERCOUNT, Events.EL_GOLD_CONSUME_TYPE + " " + i, distrGoldConsume.getConsumePlayerCount());
				EventQueue.INSTANCE.push(eventCategory, Events.EA_CONSUME_COUNT, Events.EL_GOLD_CONSUME_TYPE + " " + i, distrGoldConsume.getConsumeCount());
			}
		}
	}
}
