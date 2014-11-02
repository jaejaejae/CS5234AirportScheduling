package flight.data.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import flight.data.FlightInformation;

public class SqliteFLightRepository implements FlightRepository {
	Connection c = null;

	public SqliteFLightRepository() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:flights.db");
			c.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(FlightInformation info) {
		PreparedStatement stm;
		try {
			stm = c.prepareStatement("INSERT INTO flight (day_of_week, fl_date, fl_month, fl_year, unique_carrier,"
					+ "fl_num, origin_airport_id, dest_airport_id, crs_dep_time, dep_time"
					+ ", dep_delay, crs_arr_time, arr_time, arr_delay, cancelled, carrier_delay"
					+ ", weather_delay, nas_delay, security_delay, late_aircraft_delay)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, info.getDayOfWeek());
			stm.setInt(2, info.getFlightDate());
			stm.setInt(3, info.getFlightMonth());
			stm.setInt(4, info.getFlightYear());
			stm.setString(5, info.getUniqueCarrier());
			stm.setInt(6, info.getFlightNum());
			stm.setInt(7, info.getOriginAirportId());
			stm.setInt(8, info.getDestAirportId());
			stm.setInt(9, info.getCrsDepTime());
			stm.setInt(10, info.getDepTime());
			stm.setInt(11, info.getDepDelay());
			stm.setInt(12, info.getCrsArrTime());
			stm.setInt(13, info.getArrTime());
			stm.setInt(14, info.getArrDelay());
			stm.setInt(15, info.getCancelled());
			stm.setInt(16, info.getCarrierDelay());
			stm.setInt(17, info.getWeatherDelay());
			stm.setInt(18, info.getNasDelay());
			stm.setInt(19, info.getSecurityDelay());
			stm.setInt(20, info.getLateAircraftDelay());
			stm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Cannot execute statement");
			e.printStackTrace();
		}
	}

	@Override
	public void clear() {
		try {
			PreparedStatement stm = c.prepareStatement("DELETE FROM flight");
			stm.executeUpdate();
		} catch (SQLException e) {

		}

	}

	@Override
	public void commit() {
		try {
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
