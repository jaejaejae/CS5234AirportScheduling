package flight.scheduler.gateCollision;

import java.util.ArrayList;
import java.util.Collections;

import flight.scheduler.CustomGateInterval;
import flight.scheduler.GateInterval;

public abstract class GateCollisionCounter {
	ArrayList<GateInterval> allSortedGateIntervals;
	ArrayList<GateInterval> collisionGates = new ArrayList<>();
	int totalDelay = 0;

	public GateCollisionCounter(ArrayList<GateInterval> allSortedGateIntervals) {
		this.allSortedGateIntervals = allSortedGateIntervals;
	}

	public void begin() {
		for (int i = 0; i < allSortedGateIntervals.size() - 1; i++) {
			ArrayList<Integer> tobeRemoved = new ArrayList<Integer>();
			GateInterval gateInterval = allSortedGateIntervals.get(i);
			GateInterval nextGateInterval = allSortedGateIntervals.get(i + 1);
			int newEndTime = getNewEndTime(gateInterval);
			if (newEndTime > gateInterval.getCrsEndTime())
				totalDelay++;
			if (newEndTime > nextGateInterval.getCrsStartTime()) {
				collisionGates.add(new CustomGateInterval(nextGateInterval
						.getFlightInfo(), nextGateInterval.getCrsStartTime(),
						nextGateInterval.getCrsEndTime()));
				tobeRemoved.add(i);
			}
			Collections.reverse(tobeRemoved);
			for (int index : tobeRemoved) {
				allSortedGateIntervals.remove(index);
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

	public int getTotalDelay() {
		return totalDelay;
	}
}
