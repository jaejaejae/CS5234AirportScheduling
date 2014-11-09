package flight.scheduler.gateCollision;

import java.util.ArrayList;

import flight.scheduler.GateInterval;

public class ConstantGateCollisionCounter extends GateCollisionCounter {

	int delay;

	public ConstantGateCollisionCounter(
			ArrayList<GateInterval> allSortedGateIntervals, int delay) {
		super(allSortedGateIntervals);
		this.delay = delay;
	}

	@Override
	protected int getNewEndTime(GateInterval gateInterval) {
		return gateInterval.getCrsEndTime() + delay;
	}

}
