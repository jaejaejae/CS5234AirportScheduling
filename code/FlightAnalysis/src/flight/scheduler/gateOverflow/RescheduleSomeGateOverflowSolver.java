package flight.scheduler.gateOverflow;

import java.util.ArrayList;

import flight.scheduler.FlightScheduler;
import flight.scheduler.GateInterval;

public class RescheduleSomeGateOverflowSolver extends GateOverflowSolver {

	public RescheduleSomeGateOverflowSolver(
			ArrayList<ArrayList<GateInterval>> originalSchedule,
			ArrayList<GateInterval> collisionGates) {
		super(originalSchedule, collisionGates);
	}

	@Override
	public void begin() {
		FlightScheduler scheduler = new FlightScheduler(collisionGates);
		scheduler.startScheduling();
		ArrayList<ArrayList<GateInterval>> schedule = scheduler
				.getAllSequences();

		newSchedule = new ArrayList<ArrayList<GateInterval>>();
		newSchedule.addAll(schedule);
		newSchedule.addAll(originalSchedule);
	}

}
