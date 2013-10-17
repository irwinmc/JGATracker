package com.arthurcn;

public interface Events {

	/**
	 * Event category
	 */
	public static final String EC_OPERATION = "Operation";
	
	public static final String EC_LEVEL_DIST = "Level Distribution";
	
	public static final String EC_GOLD_CONSUME = "Gold Consumption";
	
	public static final String EC_SILVER_CONSUME = "Silver Consumption";
	
	/**
	 * Event action
	 */
	public static final String EA_LOG = "Log";
	
	public static final String EA_PLAYER_NUMBER = "Player Number";
	
	public static final String EA_CONSUME_VALUE = "Consume Value";
	public static final String EA_CONSUME_PLAYERCOUNT = "Consume Player Count";
	public static final String EA_CONSUME_COUNT = "Consume Count";
	
	/**
	 * Event label
	 */
	public static final String EL_PLAYER_CREATE_COUNT = "Player Create Count";
	public static final String EL_TOTAL_REGISTER_COUNT = "Total Register Count";
	public static final String EL_REGISTER_ACCOUNT_COUNT = "Register Account Count";
	public static final String EL_LOGIN_ACCOUNT_COUNT = "Login Account Count";
	public static final String EL_REGISTER_CONVERSION = "Register Conversion";
	public static final String EL_ENTER_GAME_PLAYER_COUNT = "Enter Game Player Count";
	public static final String EL_ONLINE_AVG_COUNT = "Online Avg Count";
	public static final String EL_ONLINE_MAX_COUNT = "Online Max Count";
	public static final String EL_TOTAL_CHARGE_MONEY = "Total Charge Money";
	public static final String EL_TOTAL_CHARGE_GOLD = "Total Charge Gold";
	public static final String EL_CHARGE_ACCOUNT_COUNT = "Charge Account Count";
	public static final String EL_CHARGE_COUNT = "Charge Count";
	public static final String EL_ARPU = "ARPU";
	public static final String EL_ACTIVE_PAY_PERMEABILITY = "Active Pay Permeability";
	
	public static final String EL_LEVEL = "Level";
	
	public static final String EL_GOLD_CONSUME_TYPE = "Gold Consume Type";
	
}
