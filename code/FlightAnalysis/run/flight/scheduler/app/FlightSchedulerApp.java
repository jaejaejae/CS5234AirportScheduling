package flight.scheduler.app;

import java.util.ArrayList;

import flight.data.DelayType;
import flight.data.FlightInformation;
import flight.data.FlightType;
import flight.data.repository.SqliteFlightRepository;
import flight.scheduler.FlightScheduler;
import flight.scheduler.GateInterval;
import flight.scheduler.util.FlightToGateInterval;

public class FlightSchedulerApp {
	public static final int DFW_AIRPORT_ID = 11298;

	int airportId = DFW_AIRPORT_ID;
	int date = 1;
	int month = 1;
	int year = 2014;

	FlightScheduler scheduler;
	int totalFlights = 0;

	public static void main(String[] args) {
		new FlightSchedulerApp(DFW_AIRPORT_ID, 1, 1, 2014).begin();
	}

	public FlightSchedulerApp(int airportId, int date, int month, int year) {
		this.airportId = airportId;
		this.date = date;
		this.month = month;
		this.year = year;
	}

	public void begin() {
		SqliteFlightRepository repository = new SqliteFlightRepository();
		FlightInformation flightConstraint = new FlightInformation(0, date,
				month, year, null, 0, airportId, airportId, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0);
		ArrayList<Integer> constraintIndices = new ArrayList<>();
		constraintIndices.add(FlightInformation.FLIGHT_DATE_INDEX);
		constraintIndices.add(FlightInformation.FLIGHT_MONTH_INDEX);
		constraintIndices.add(FlightInformation.FLIGHT_YEAR_INDEX);
		constraintIndices.add(FlightInformation.ORIGIN_AIRPORT_ID);
		ArrayList<FlightInformation> takingoffFlights = repository.getFlights(
				flightConstraint, constraintIndices);

		constraintIndices.set(3, FlightInformation.DEST_AIRPORT_ID);
		ArrayList<FlightInformation> arrivalFlights = repository.getFlights(
				flightConstraint, constraintIndices);

		ArrayList<GateInterval> gateIntervals = new ArrayList<>();

		for (FlightInformation takingoffFlight : takingoffFlights) {
			gateIntervals.add(new FlightToGateInterval(takingoffFlight,
					FlightType.Departure, DelayType.NoDelay, 0)
					.getGateInterval());
		}

		for (FlightInformation arrivalFlight : arrivalFlights) {
			gateIntervals
					.add(new FlightToGateInterval(arrivalFlight,
							FlightType.Arrival, DelayType.NoDelay, 0)
							.getGateInterval());
		}

		totalFlights = gateIntervals.size();
		scheduler = new FlightScheduler(gateIntervals);
		scheduler.startScheduling();

		// System.out.println("Total gates: " + scheduler.getTotalGates());
		// System.out.println("Gate(0): " + scheduler.getSequences(0));
		// System.out.println("Gate(1): " + scheduler.getSequences(1));
		// System.out.println("Gate(2): " + scheduler.getSequences(2));
	}

	public FlightScheduler getScheduler() {
		return scheduler;
	}

	public int getTotalGates() {
		return getScheduler().getTotalGates();
	}

	public int getTotalFlights() {
		return totalFlights;
	}
}
