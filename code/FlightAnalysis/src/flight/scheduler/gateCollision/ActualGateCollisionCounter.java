package flight.scheduler.gateCollision;

import java.util.ArrayList;

import flight.scheduler.GateInterval;

public class ActualGateCollisionCounter extends GateCollisionCounter {

	public ActualGateCollisionCounter(ArrayList<GateInterval> allSortedGates) {
		super(allSortedGates);
	}

	@Override
	protected int getNewEndTime(GateInterval gateInterval) {
		return gateInterval.getDelayEndTime();
	}

}
