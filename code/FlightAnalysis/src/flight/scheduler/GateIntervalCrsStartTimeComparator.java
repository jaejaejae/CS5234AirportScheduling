package flight.scheduler;

import java.util.Comparator;

public class GateIntervalCrsStartTimeComparator implements
		Comparator<GateInterval> {
	@Override
	public int compare(GateInterval o1, GateInterval o2) {
		return o1.getCrsStartTime() - o2.getCrsStartTime();
	}
}