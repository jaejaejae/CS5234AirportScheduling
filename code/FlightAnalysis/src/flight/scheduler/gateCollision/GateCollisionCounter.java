package flight.scheduler.gateCollision;

import java.util.ArrayList;

import flight.scheduler.GateInterval;

public abstract class GateCollisionCounter {
	ArrayList<GateInterval> allSortedGateIntervals;
	ArrayList<GateInterval> collisionGates = new ArrayList<>();

	public GateCollisionCounter(ArrayList<GateInterval> allSortedGateIntervals) {
		this.allSortedGateIntervals = allSortedGateIntervals;
	}

	public void begin() {
		for (int i = 0; i < allSortedGateIntervals.size() - 1; i++) {
			GateInterval gateInterval = allSortedGateIntervals.get(i);
			GateInterval nextGateInterval = allSortedGateIntervals.get(i + 1);
			int newEndTime = getNewEndTime(gateInterval);
			if (newEndTime > nextGateInterval.getCrsStartTime()) {
				collisionGates.add(gateInterval);
			}
		}
	}

	protected abstract int getNewEndTime(GateInterval gateInterval);

	public int getTotalCollision() {
		return collisionGates.size();
	}

	public ArrayList<GateInterval> getAllCollisionGates() {
		return collisionGates;
	}
}
