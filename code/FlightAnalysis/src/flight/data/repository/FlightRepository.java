package flight.data.repository;

import java.util.ArrayList;

import flight.data.FlightInformation;

public interface FlightRepository {
	public void insert(FlightInformation info);
	public ArrayList<FlightInformation> getFlights(FlightInformation constraint, ArrayList<Integer> constraintIndices);
}
