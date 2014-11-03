package flight.data.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import flight.data.FlightInformation;

public class SqliteFlightRepository implements FlightRepository {
	Connection c = null;

	public SqliteFlightRepository() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:flights.db");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void setAutoCommit(boolean b) {
		try {
			c.setAutoCommit(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Integer> getDistinct(int constraintIndex) {
		String sql = getDistinctSql(constraintIndex);
		ArrayList<Integer> distinctValues = new ArrayList<Integer>();

		try {
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				int value = rs.getInt(1);
				distinctValues.add(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return distinctValues;
	}

	public void clear() {
		try {
			PreparedStatement stm = c.prepareStatement("DELETE FROM flight");
			stm.executeUpdate();
		} catch (SQLException e) {

		}

	}

	public void commit() {
		try {
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<FlightInformation> getFlights(
			FlightInformation flightConstraint,
			ArrayList<Integer> constraintIndices) {
		String sql = createSqlQuery(flightConstraint, constraintIndices);
		ArrayList<FlightInformation> flights = new ArrayList<>();
		try {
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				int dayOfWeek = rs.getInt("day_of_week");
				int flightDate = rs.getInt("fl_date");
				int flightMonth = rs.getInt("fl_month");
				int flightYear = rs.getInt("fl_year");
				String uniqueCarrier = rs.getString("unique_carrier");
				int flightNum = rs.getInt("fl_num");
				int originAirportId = rs.getInt("origin_airport_id");
				int destAirportId = rs.getInt("dest_airport_id");
				int crsDepTime = rs.getInt("crs_dep_time");
				int depTime = rs.getInt("dep_time");
				int depDelay = rs.getInt("dep_delay");
				int crsArrTime = rs.getInt("crs_arr_time");
				int arrTime = rs.getInt("arr_time");
				int arrDelay = rs.getInt("arr_delay");
				int cancelled = rs.getInt("cancelled");
				int carrierDelay = rs.getInt("carrier_delay");
				int weatherDelay = rs.getInt("weather_delay");
				int nasDelay = rs.getInt("nas_delay");
				int securityDelay = rs.getInt("security_delay");
				int lateAircraftDelay = rs.getInt("late_aircraft_delay");

				FlightInformation info = new FlightInformation(dayOfWeek,
						flightDate, flightMonth, flightYear, uniqueCarrier,
						flightNum, originAirportId, destAirportId, crsDepTime,
						depTime, depDelay, crsArrTime, arrTime, arrDelay,
						cancelled, -1, -1, -1, carrierDelay, weatherDelay,
						nasDelay, securityDelay, lateAircraftDelay);

				flights.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}

	private String createSqlQuery(FlightInformation flightConstraint,
			ArrayList<Integer> constraintIndices) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT day_of_week, fl_date, fl_month, fl_year, unique_carrier,"
				+ "fl_num, origin_airport_id, dest_airport_id, crs_dep_time, dep_time"
				+ ", dep_delay, crs_arr_time, arr_time, arr_delay, cancelled, carrier_delay"
				+ ", weather_delay, nas_delay, security_delay, late_aircraft_delay"
				+ " FROM flight ");
		if (constraintIndices.size() == 0)
			return sb.toString();

		sb.append(" WHERE ");
		boolean isFirst = true;
		for (int index : constraintIndices) {
			if (!isFirst) {
				sb.append(" AND ");
			} else {
				isFirst = false;
			}
			switch (index) {
			case FlightInformation.DAY_OF_WEEK_INDEX:
				sb.append(String.format(" day_of_week = %d ",
						flightConstraint.getDayOfWeek()));
				break;
			case FlightInformation.FLIGHT_DATE_INDEX:
				sb.append(String.format(" fl_date = %d ",
						flightConstraint.getFlightDate()));
				break;
			case FlightInformation.FLIGHT_MONTH_INDEX:
				sb.append(String.format(" fl_month = %d ",
						flightConstraint.getFlightMonth()));
				break;
			case FlightInformation.FLIGHT_YEAR_INDEX:
				sb.append(String.format(" fl_year = %d ",
						flightConstraint.getFlightYear()));
				break;
			case FlightInformation.UNIQUE_CARRIER_INDEX:
				sb.append(String.format(" unique_carrier LIKE '%%%s%%' ",
						flightConstraint.getUniqueCarrier()));
				break;
			case FlightInformation.FLIGHT_NUM:
				sb.append(String.format(" fl_num = %d ",
						flightConstraint.getFlightNum()));
				break;
			case FlightInformation.ORIGIN_AIRPORT_ID:
				sb.append(String.format(" origin_airport_id = %d ",
						flightConstraint.getOriginAirportId()));
				break;
			case FlightInformation.DEST_AIRPORT_ID:
				sb.append(String.format(" dest_airport_id = %d ",
						flightConstraint.getDestAirportId()));
				break;
			case FlightInformation.CRS_DEP_TIME:
				sb.append(String.format(" crs_dep_time = %d ",
						flightConstraint.getCrsDepTime()));
				break;
			case FlightInformation.DEP_TIME:
				sb.append(String.format(" dep_time = %d ",
						flightConstraint.getDepTime()));
				break;
			case FlightInformation.DEP_DELAY:
				sb.append(String.format(" dep_delay = %d ",
						flightConstraint.getDepDelay()));
				break;
			case FlightInformation.CRS_ARR_TIME:
				sb.append(String.format(" crs_arr_time = %d ",
						flightConstraint.getCrsArrTime()));
				break;
			case FlightInformation.ARR_TIME:
				sb.append(String.format(" arr_time = %d ",
						flightConstraint.getArrTime()));
				break;
			case FlightInformation.ARR_DELAY:
				sb.append(String.format(" arr_delay = %d ",
						flightConstraint.getArrDelay()));
				break;
			case FlightInformation.CANCELLED:
				sb.append(String.format(" cancelled = %d ",
						flightConstraint.getCancelled()));
				break;
			case FlightInformation.CARRIER_DELAY:
				sb.append(String.format(" carrier_delay = %d ",
						flightConstraint.getCarrierDelay()));
				break;
			case FlightInformation.WEATHER_DELAY:
				sb.append(String.format(" weather_delay = %d ",
						flightConstraint.getWeatherDelay()));
				break;
			case FlightInformation.NAS_DELAY:
				sb.append(String.format(" nas_delay = %d ",
						flightConstraint.getNasDelay()));
				break;
			case FlightInformation.SECURITY_DELAY:
				sb.append(String.format(" security_delay = %d ",
						flightConstraint.getSecurityDelay()));
				break;
			case FlightInformation.LATE_AIRCRAFT_DELAY:
				sb.append(String.format(" late_aircraft_delay = %d ",
						flightConstraint.getLateAircraftDelay()));
				break;
			default:
				return null;
			}
		}
		return sb.toString();
	}

	public String getDistinctSql(int constraintIndex) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT(");
		switch (constraintIndex) {
		case FlightInformation.DAY_OF_WEEK_INDEX:
			sb.append(String.format(" day_of_week "));
			break;
		case FlightInformation.FLIGHT_DATE_INDEX:
			sb.append(String.format(" fl_date "));
			break;
		case FlightInformation.FLIGHT_MONTH_INDEX:
			sb.append(String.format(" fl_month "));
			break;
		case FlightInformation.FLIGHT_YEAR_INDEX:
			sb.append(String.format(" fl_year "));
			break;
		case FlightInformation.UNIQUE_CARRIER_INDEX:
			sb.append(String.format(" unique_carrier "));
			break;
		case FlightInformation.FLIGHT_NUM:
			sb.append(String.format(" fl_num "));
			break;
		case FlightInformation.ORIGIN_AIRPORT_ID:
			sb.append(String.format(" origin_airport_id "));
			break;
		case FlightInformation.DEST_AIRPORT_ID:
			sb.append(String.format(" dest_airport_id "));
			break;
		case FlightInformation.CRS_DEP_TIME:
			sb.append(String.format(" crs_dep_time "));
			break;
		case FlightInformation.DEP_TIME:
			sb.append(String.format(" dep_time  "));
			break;
		case FlightInformation.DEP_DELAY:
			sb.append(String.format(" dep_delay  "));
			break;
		case FlightInformation.CRS_ARR_TIME:
			sb.append(String.format(" crs_arr_time "));
			break;
		case FlightInformation.ARR_TIME:
			sb.append(String.format(" arr_time  "));
			break;
		case FlightInformation.ARR_DELAY:
			sb.append(String.format(" arr_delay  "));
			break;
		case FlightInformation.CANCELLED:
			sb.append(String.format(" cancelled "));
			break;
		case FlightInformation.CARRIER_DELAY:
			sb.append(String.format(" carrier_delay "));
			break;
		case FlightInformation.WEATHER_DELAY:
			sb.append(String.format(" weather_delay  "));
			break;
		case FlightInformation.NAS_DELAY:
			sb.append(String.format(" nas_delay  "));
			break;
		case FlightInformation.SECURITY_DELAY:
			sb.append(String.format(" security_delay  "));
			break;
		case FlightInformation.LATE_AIRCRAFT_DELAY:
			sb.append(String.format(" late_aircraft_delay  "));
			break;
		default:
			return null;
		}
		sb.append(") from flight");
		return sb.toString();
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
}
