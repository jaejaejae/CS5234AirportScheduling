package flight.scheduler.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import flight.data.FlightInformation;
import flight.data.repository.SqliteFlightRepository;
import flight.scheduler.FlightScheduler;
import flight.scheduler.app.FlightSchedulerApp;
import flight.scheduler.gateCollision.ConstantGateCollisionCounter;
import flight.scheduler.gateCollision.GateCollisionCounter;

public class GateCollisionApp {
	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(
					"totalFlights_totalGateCollisions_constant.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Integer> airports = new SqliteFlightRepository()
				.getDistinct(FlightInformation.ORIGIN_AIRPORT_ID);
		for (int constantDelay = 1; constantDelay <= 50; constantDelay += 5) {
			int totalCollisions = 0;
			for (int airport : airports) {
				for (int i = 1; i <= 1; i++) {
					FlightSchedulerApp flightSchedulerApp = new FlightSchedulerApp(
							airport, i, 1, 2014);
					flightSchedulerApp.begin();

					totalCollisions += getTotalCollisions(
							flightSchedulerApp.getScheduler(), constantDelay);

				}
			}
			pw.println(String.format("%d,%d", constantDelay, totalCollisions));
		}

		pw.close();
	}

	private static int getTotalCollisions(FlightScheduler scheduler,
			int constantDelay) {
		int sum = 0;
		for (int i = 0; i < scheduler.getTotalGates(); i++) {
			GateCollisionCounter gateCounter = new ConstantGateCollisionCounter(
					scheduler.getSequences(i), constantDelay);
			gateCounter.begin();
			sum += gateCounter.getTotalCollision();
		}
		return sum;
	}
}
