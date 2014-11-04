package flight.scheduler.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import flight.data.FlightInformation;
import flight.data.repository.SqliteFlightRepository;
import flight.scheduler.app.FlightSchedulerApp;

public class GateFlightApp {
	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(
					"totalFlights_totalGates_delay_optimal.txt"));
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

				pw.println(String.format("%d, %d",
						flightSchedulerApp.getTotalFlights(),
						flightSchedulerApp.getTotalGates()));
				count++;
				System.out.println(count);
			}
			if (count > 1000)
				break;
		}

		pw.close();
	}
}
