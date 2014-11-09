package flight.scheduler.gateOverflow;

import java.util.ArrayList;

import flight.scheduler.FlightScheduler;
import flight.scheduler.GateInterval;

public class RescheduleAllGateOverflowSolver extends GateOverflowSolver {

	public RescheduleAllGateOverflowSolver(
			ArrayList<ArrayList<GateInterval>> originalSchedule,
			ArrayList<GateInterval> collisionGates) {
		super(originalSchedule, collisionGates);
	}

	@Override
	public void begin() {
		totalReassignment = -1;

		ArrayList<GateInterval> allGates = new ArrayList<>();
		allGates.addAll(collisionGates);

		for (ArrayList<GateInterval> gateIntervals : originalSchedule) {
			allGates.addAll(gateIntervals);
		}

		FlightScheduler scheduler = new FlightScheduler(allGates);
		scheduler.startScheduling();
		newSchedule = scheduler.getAllSequences();
	}
}
