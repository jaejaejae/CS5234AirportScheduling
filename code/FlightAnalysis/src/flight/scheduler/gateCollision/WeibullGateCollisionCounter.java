package flight.scheduler.gateCollision;

import java.util.ArrayList;

import jdistlib.Weibull;
import flight.scheduler.GateInterval;
import flight.scheduler.util.TimeUtil;

public class WeibullGateCollisionCounter extends GateCollisionCounter {
	Weibull randomDelay = new Weibull(0.7, 20);

	public WeibullGateCollisionCounter(ArrayList<GateInterval> allSortedGates) {
		super(allSortedGates);
	}

	@Override
	protected int getNewEndTime(GateInterval gateInterval) {
		int delay = (int) randomDelay.random();
		int originalEndTime = gateInterval.getCrsEndTime();
		int newEndTime = TimeUtil.addTime(originalEndTime, delay);

		return newEndTime;
	}

}
