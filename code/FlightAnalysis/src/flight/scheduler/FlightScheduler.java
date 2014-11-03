package flight.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FlightScheduler {
	List<GateInterval> gateIntervals;
	PriorityQueue<GateIntervalPair> pq;

	ArrayList<ArrayList<GateInterval>> scheduledGates = new ArrayList<>();

	public FlightScheduler(List<GateInterval> gateIntervals) {
		this.gateIntervals = gateIntervals;
	}

	public void startScheduling() {
		Collections.sort(gateIntervals,
				new GateIntervalCrsStartTimeComparator());
		pq = new PriorityQueue<>(new GateIntervalPairCrsEndTimeComparator());

		for (GateInterval interval : gateIntervals) {
			int gateIndex = findEmptyGate(pq, interval);
			if (gateIndex == -1) {
				gateIndex = scheduledGates.size();
				scheduledGates.add(new ArrayList<GateInterval>());
			}
			ArrayList<GateInterval> gate = scheduledGates.get(gateIndex);
			gate.add(interval);
			pq.add(new GateIntervalPair(interval, gateIndex));
		}
	}

	private int findEmptyGate(PriorityQueue<GateIntervalPair> pq,
			GateInterval interval) {
		if (pq.isEmpty())
			return -1;
		GateIntervalPair pair = pq.peek();
		if (pair.interval.getCrsEndTime() > interval.getCrsStartTime())
			return -1;
		pq.poll();
		return pair.index;
	}

	public int getTotalGates() {
		return scheduledGates.size();
	}

	public ArrayList<GateInterval> getSequences(int i) {
		return scheduledGates.get(i);
	}

	class GateIntervalCrsStartTimeComparator implements
			Comparator<GateInterval> {
		@Override
		public int compare(GateInterval o1, GateInterval o2) {
			return o1.getCrsStartTime() - o2.getCrsStartTime();
		}
	}

	class GateIntervalPairCrsEndTimeComparator implements
			Comparator<GateIntervalPair> {

		@Override
		public int compare(GateIntervalPair arg0, GateIntervalPair arg1) {
			return arg0.interval.getCrsEndTime()
					- arg1.interval.getCrsEndTime();
		}
	}

	class GateIntervalPair {
		public GateInterval interval;
		public int index;

		public GateIntervalPair(GateInterval interval, int index) {
			this.interval = interval;
			this.index = index;
		}
	}

}
