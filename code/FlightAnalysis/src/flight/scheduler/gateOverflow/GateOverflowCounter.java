package flight.scheduler.gateOverflow;

import java.util.ArrayList;

import flight.scheduler.FlightScheduler;
import flight.scheduler.GateInterval;
import flight.scheduler.gateCollision.ActualGateCollisionCounter;
import flight.scheduler.gateCollision.GateCollisionCounter;

public class GateOverflowCounter {
	ArrayList<ArrayList<GateInterval>> sequences;
	int totalOverFlow = -1;

	public GateOverflowCounter(ArrayList<ArrayList<GateInterval>> sequences) {
		this.sequences = sequences;
	}

	public void begin() {
		ArrayList<GateInterval> allCollisionGates = new ArrayList<>();
		for (int i = 0; i < sequences.size(); i++) {
			GateCollisionCounter gateCollisionCounter = new ActualGateCollisionCounter(
					sequences.get(i));
			gateCollisionCounter.begin();
			allCollisionGates.addAll(gateCollisionCounter
					.getAllCollisionGates());
		}

		// System.out.println(allCollisionGates);
		FlightScheduler overflowScheduler = new FlightScheduler(
				allCollisionGates);
		overflowScheduler.startScheduling();
		totalOverFlow = overflowScheduler.getTotalGates();
	}

	public int getTotalOverflow() {
		return totalOverFlow;
	}
}
