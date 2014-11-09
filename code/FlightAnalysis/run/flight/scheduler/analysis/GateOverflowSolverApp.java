package flight.scheduler.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import flight.data.FlightInformation;
import flight.data.repository.SqliteFlightRepository;
import flight.scheduler.FlightScheduler;
import flight.scheduler.GateInterval;
import flight.scheduler.app.FlightSchedulerApp;
import flight.scheduler.gateCollision.ActualGateCollisionCounter;
import flight.scheduler.gateCollision.GateCollisionCounter;
import flight.scheduler.gateOverflow.GateOverflowSolver;
import flight.scheduler.gateOverflow.RescheduleInsertGateOverflowSolver;

public class GateOverflowSolverApp {

	private static ArrayList<GateInterval> collisionGates;
	private static ArrayList<ArrayList<GateInterval>> originalScheduleRemovingCollision = new ArrayList<ArrayList<GateInterval>>();
	private static ArrayList<ArrayList<GateInterval>> newSchedule;

	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("new_overflow_tau_50.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int count = 0;
		ArrayList<Integer> airports = new SqliteFlightRepository()
				.getDistinct(FlightInformation.ORIGIN_AIRPORT_ID);
		for (int airport : airports) {
			for (int i = 1; i <= 1; i++) {
				FlightSchedulerApp flightSchedulerApp = new FlightSchedulerApp(
						airport, i, 1, 2014, 50);
				flightSchedulerApp.begin();
				int originalTotalGate = flightSchedulerApp.getTotalGates();

				int totalCollisions = getTotalCollisions(flightSchedulerApp
						.getScheduler());
				int totalOverflow = getTotalOverflow(flightSchedulerApp);

				pw.println(String.format("%d, %d, %d, %d",
						flightSchedulerApp.getTotalFlights(),
						originalTotalGate, totalCollisions, totalOverflow));
				count++;
				System.out.println(count);
			}
			if (count > 1000)
				break;
		}

		pw.close();
	}

	private static int getTotalOverflow(FlightSchedulerApp flightSchedulerApp) {
		GateOverflowSolver overflowCounter = new RescheduleInsertGateOverflowSolver(
				originalScheduleRemovingCollision, collisionGates);
		overflowCounter.begin();
		newSchedule = overflowCounter.getNewSchedule();
		int totalOverflow = overflowCounter.getTotalOverflow();
		return totalOverflow;
	}

	private static int getTotalCollisions(FlightScheduler scheduler) {
		int sum = 0;
		originalScheduleRemovingCollision = new ArrayList<ArrayList<GateInterval>>();
		collisionGates = new ArrayList<GateInterval>();
		for (int i = 0; i < scheduler.getTotalGates(); i++) {
			GateCollisionCounter gateCounter = new ActualGateCollisionCounter(
					scheduler.getSequences(i));
			gateCounter.begin();
			collisionGates.addAll(gateCounter.getAllCollisionGates());
			originalScheduleRemovingCollision.add(gateCounter.getSchedules());
			sum += gateCounter.getTotalCollision();
		}
		return sum;
	}

}
