package flight.scheduler.gateOverflow;

import java.util.ArrayList;
import java.util.Collections;

import flight.scheduler.GateInterval;
import flight.scheduler.GateIntervalCrsStartTimeComparator;

public class GateOverflowAlgo4 {
	// with removed collision gate.
	ArrayList<ArrayList<GateInterval>> scheduledGateIntervals;
	ArrayList<Integer> currentIndices = new ArrayList<>();
	ArrayList<GateInterval> collisionGateIntervals;

	public GateOverflowAlgo4(
			ArrayList<ArrayList<GateInterval>> scheduledGateIntervals,
			ArrayList<GateInterval> collisionGateIntervals) {
		this.scheduledGateIntervals = scheduledGateIntervals;
		this.collisionGateIntervals = collisionGateIntervals;

		Collections.sort(this.collisionGateIntervals,
				new GateIntervalCrsStartTimeComparator());
	}

	public void begin() {
		prepareDataStructure();
		for (GateInterval collisionGateInterval : collisionGateIntervals) {
			ArrayList<GateInterval> selectedGateInterval = null;
			int index = -1;
			for (int g = 0; g < scheduledGateIntervals.size(); g++) {
				ArrayList<GateInterval> gateIntervals = scheduledGateIntervals
						.get(g);

				while (currentIndices.get(g) < gateIntervals.size()
						&& gateIntervals.get(currentIndices.get(g))
								.getCrsEndTime() < collisionGateInterval
								.getCrsStartTime()) {
					currentIndices.set(g, currentIndices.get(g) + 1);
				}

				if (currentIndices.get(g) >= gateIntervals.size()
						|| gateIntervals.get(currentIndices.get(g))
								.getCrsStartTime() > collisionGateInterval
								.getCrsEndTime()) {
					selectedGateInterval = gateIntervals;
					index = currentIndices.get(g);
					break;
				}
			}
			if (selectedGateInterval == null) {
				selectedGateInterval = new ArrayList<GateInterval>();
				scheduledGateIntervals.add(selectedGateInterval);
				currentIndices.add(0);
				index = 0;
			}

			selectedGateInterval.add(index, collisionGateInterval);
		}
	}

	private void prepareDataStructure() {
		for (int g = 0; g < scheduledGateIntervals.size(); g++) {
			currentIndices.add(0);
		}

	}

	public int getTotalGates() {
		return scheduledGateIntervals.size();
	}

	public ArrayList<GateInterval> getGateIntervals(int i) {
		return scheduledGateIntervals.get(i);
	}
}
