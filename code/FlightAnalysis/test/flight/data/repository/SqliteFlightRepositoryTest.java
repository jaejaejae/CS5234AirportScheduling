package flight.data.repository;

import java.util.ArrayList;

import flight.data.FlightInformation;

public class SqliteFlightRepositoryTest {
	public static void main(String[] args) {
		SqliteFlightRepository repository = new SqliteFlightRepository();
		ArrayList<Integer> constraintIndices = new ArrayList<>();
		constraintIndices.add(FlightInformation.UNIQUE_CARRIER_INDEX);
		FlightInformation flightConstraint = new FlightInformation(0, 0, 0, 0,
				"AA", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		ArrayList<FlightInformation> results = repository.getFlights(
				flightConstraint, constraintIndices);
		for (FlightInformation info : results)
			System.out.println(info);
	}
}
