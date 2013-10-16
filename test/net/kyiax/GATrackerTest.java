package net.kyiax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.kyiax.analytic.BriefOperate;
import net.kyiax.analytic.DistrGoldConsume;
import net.kyiax.util.IntegerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GATrackerTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(GATrackerTest.class);
	
	private static BriefOperate briefOperate;
	private static int maxLevel = 100;
	private static Map<Integer, Integer> levelDistribution;
	private static List<DistrGoldConsume> goldConsumeDistribution;
	
	static {
		prepareData();
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		EventQueue.INSTANCE.init();
		EventQueue.INSTANCE.start();
		
		GATracker tracker = new GATracker();
//		tracker.trackBriefOperate(briefOperate);
//		tracker.trackDistrLevel(maxLevel, levelDistribution);
		tracker.trackDistrGoldConsume(goldConsumeDistribution);
		
		LOG.info("Track complete. You can open google analytic to view.");
	}
	
	/**
	 * Prepare data
	 */
	private static void prepareData() {
		// Operation brief data
		briefOperate = new BriefOperate();
		briefOperate.setPlayerCreateCount(getRandomInt());
		briefOperate.setTotalRegisterCount(getRandomInt());
		briefOperate.setRegisterConversion(getRandomInt());
		briefOperate.setEnterGamePlayerCount(getRandomInt());
		briefOperate.setLoginAccountCount(getRandomInt());
		briefOperate.setOnlineAvgCount(getRandomInt());
		briefOperate.setOnlineMaxCount(getRandomInt());
		briefOperate.setTotalChargeMoney(getRandomInt());
		briefOperate.setTotalChargeGold(getRandomInt());
		briefOperate.setChargeAccountCount(getRandomInt());
		briefOperate.setChargeCount(getRandomInt());
		briefOperate.setArpu(getRandomInt());
		briefOperate.setActivePayPermeability(getRandomInt());
		
		// Level distribution
		levelDistribution = new HashMap<Integer, Integer>();
		for (int i = 1; i <= maxLevel; i++) {
			levelDistribution.put(i, getRandomInt());
		}
		
		// Gold consume distribution
		goldConsumeDistribution = new ArrayList<DistrGoldConsume>();
		for (int type = 1; type <= 10; type++) {
			DistrGoldConsume distrGoldConsume = new DistrGoldConsume();
			distrGoldConsume.setType(type);
			distrGoldConsume.setConsumeValue(getRandomInt());
			distrGoldConsume.setConsumePlayerCount(getRandomInt());
			distrGoldConsume.setConsumeCount(getRandomInt());
			goldConsumeDistribution.add(distrGoldConsume);
		}
	}
	
	/**
	 * Get random integer
	 * 
	 * @return
	 */
	private static int getRandomInt() {
		return IntegerUtils.generateRandomSeed(0, 100000);
	}
}
