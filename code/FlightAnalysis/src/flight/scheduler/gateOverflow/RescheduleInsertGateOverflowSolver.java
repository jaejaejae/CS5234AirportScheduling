package flight.scheduler.gateOverflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import flight.scheduler.GateInterval;
import flight.scheduler.GateIntervalCrsStartTimeComparator;
import flight.util.IntegerPair;

public class RescheduleInsertGateOverflowSolver extends GateOverflowSolver {

	ArrayList<Integer> currentIndex = new ArrayList<>();

	public RescheduleInsertGateOverflowSolver(
			ArrayList<ArrayList<GateInterval>> originalSchedule,
			ArrayList<GateInterval> collisionGates) {
		super(originalSchedule, collisionGates);
	}

	@Override
	public void begin() {
		PriorityQueue<IntegerPair> pq = new PriorityQueue<>();

		newSchedule = new ArrayList<ArrayList<GateInterval>>();
		newSchedule.addAll(originalSchedule);

		currentIndex = new ArrayList<Integer>();
		int gateIndex = 0;
		for (ArrayList<GateInterval> schedule : originalSchedule) {
			currentIndex.add(0);
			pq.add(new IntegerPair(schedule.get(0).getDelayEndTime(),
					gateIndex++));
		}

		Collections.sort(collisionGates,
				new GateIntervalCrsStartTimeComparator());

		for (GateInterval collision : collisionGates) {
			totalReassignment++;
			int startTime = collision.getCrsStartTime();

			int groupIndex = -1;
			int addIndex = -1;
			for (int g = 0; g < newSchedule.size(); g++) {
				int index = currentIndex.get(g);

				while (index < newSchedule.get(g).size()
						&& newSchedule.get(g).get(index).getDelayEndTime() < startTime) {
					index++;
					currentIndex.set(g, index);
				}

				if (index == newSchedule.get(g).size()) {
					groupIndex = g;
					addIndex = index;
					break;
				} else {
					if (newSchedule.get(g).get(index).getCrsStartTime() > collision
							.getDelayEndTime()) {
						groupIndex = g;
						addIndex = index;
						break;
					}
					continue;
				}
			}

			if (groupIndex == -1) {
				ArrayList<GateInterval> newGates = new ArrayList<>();
				newGates.add(collision);
				newSchedule.add(newGates);
				currentIndex.add(0);
			} else {
				newSchedule.get(groupIndex).add(addIndex, collision);
				currentIndex.set(groupIndex, addIndex);
			}
		}
	}
}
