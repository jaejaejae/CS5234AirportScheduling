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
import flight.scheduler.gateOverflow.GateOverflowAlgo4;

public class GateOverflowAlgo4App {
	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("gateOverFlowAlgo4.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int count = 0;
		ArrayList<Integer> airports = new SqliteFlightRepository()
				.getDistinct(FlightInformation.ORIGIN_AIRPORT_ID);
		for (int airport : airports) {
			for (int i = 1; i <= 1; i++) {
				FlightSchedulerApp flightSchedulerApp = new FlightSchedulerApp(
						airport, i, 1, 2014);
				flightSchedulerApp.begin();

				FlightScheduler scheduler = flightSchedulerApp.getScheduler();
				int beginingGate = scheduler.getTotalGates();
				ArrayList<GateInterval> collisionGateIntervals = new ArrayList<>();
				int sum = 0;
				for (int g = 0; g < scheduler.getTotalGates(); g++) {
					GateCollisionCounter gateCounter = new ActualGateCollisionCounter(
							scheduler.getSequences(g));
					gateCounter.begin();
					sum += gateCounter.getTotalCollision();
					collisionGateIntervals.addAll(gateCounter
							.getAllCollisionGates());
				}

				int totalCollisions = sum;

				GateOverflowAlgo4 gateOverflow = new GateOverflowAlgo4(
						flightSchedulerApp.getScheduler().getAllSequences(),
						collisionGateIntervals);
				gateOverflow.begin();
				int endGate = gateOverflow.getTotalGates();
				int totalOverflow = endGate - beginingGate;
				pw.println(String.format("%d, %d, %d, %d",
						flightSchedulerApp.getTotalFlights(), beginingGate,
						totalCollisions, totalOverflow));
				// System.out.println(String.format("%d, %d, %d, %d",
				// flightSchedulerApp.getTotalFlights(), beginingGate,
				// totalCollisions, totalOverflow));
				// System.out.println(gateOverflow.getGateIntervals(0));
				count++;
				System.out.println(count);
			}
			if (count > 1000)
				break;
		}

		pw.close();
	}
}
