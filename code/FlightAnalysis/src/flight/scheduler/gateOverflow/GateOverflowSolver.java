package flight.scheduler.gateOverflow;

import java.util.ArrayList;

import flight.scheduler.GateInterval;

public abstract class GateOverflowSolver {
	int totalReassignment = 0;
	ArrayList<ArrayList<GateInterval>> newSchedule = new ArrayList<>();
	ArrayList<ArrayList<GateInterval>> originalSchedule;
	ArrayList<GateInterval> collisionGates;

	public GateOverflowSolver(
			ArrayList<ArrayList<GateInterval>> originalSchedule,
			ArrayList<GateInterval> collisionGates) {
		this.originalSchedule = originalSchedule;
		this.collisionGates = collisionGates;
	}

	public abstract void begin();

	public ArrayList<ArrayList<GateInterval>> getNewSchedule() {
		return newSchedule;
	}

	public int getTotalOverflow() {
		return newSchedule.size() - originalSchedule.size();
	}

	public int getTotalReassignments() {
		return totalReassignment;
	}
}
