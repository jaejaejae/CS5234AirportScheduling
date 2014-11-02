package flight.data.repository;

import flight.data.FlightInformation;

public interface FlightRepository {
	public void insert(FlightInformation info);

	public void clear();

	public void commit();
}
