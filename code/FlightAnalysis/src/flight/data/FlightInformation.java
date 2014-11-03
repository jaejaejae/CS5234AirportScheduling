package flight.data;

public class FlightInformation {

	public static final int DAY_OF_WEEK_INDEX = 1;
	public static final int FLIGHT_DATE_INDEX = 2;
	public static final int FLIGHT_MONTH_INDEX = 3;
	public static final int FLIGHT_YEAR_INDEX = 4;
	public static final int UNIQUE_CARRIER_INDEX = 5;
	public static final int FLIGHT_NUM = 6;
	public static final int ORIGIN_AIRPORT_ID = 7;
	public static final int DEST_AIRPORT_ID = 8;
	public static final int CRS_DEP_TIME = 9;
	public static final int DEP_TIME = 10;
	public static final int DEP_DELAY = 11;
	public static final int CRS_ARR_TIME = 12;
	public static final int ARR_TIME = 13;
	public static final int ARR_DELAY = 14;
	public static final int CANCELLED = 15;
	public static final int CARRIER_DELAY = 16;
	public static final int WEATHER_DELAY = 17;
	public static final int NAS_DELAY = 18;
	public static final int SECURITY_DELAY = 19;
	public static final int LATE_AIRCRAFT_DELAY = 20;

	int dayOfWeek;
	int flightDate;
	int flightMonth;
	int flightYear;
	int flightNum;
	int originAirportId;
	int destAirportId;
	int crsDepTime;
	int depTime;
	int depDelay;
	int crsArrTime;
	int arrTime;
	int arrDelay;
	int cancelled;
	int crsElapsedTime;
	int actualElapsedTime;
	int distance;
	int carrierDelay;
	int weatherDelay;
	int nasDelay;
	int securityDelay;
	int lateAircraftDelay;
	String uniqueCarrier;

	public FlightInformation(int dayOfWeek, int flightDate, int flightMonth,
			int flightYear, String uniqueCarrier, int flightNum,
			int originAirportId, int destAirportId, int crsDepTime,
			int depTime, int depDelay, int crsArrTime, int arrTime,
			int arrDelay, int cancelled, int crsElapsedTime,
			int actualElapsedTime, int distance, int carrierDelay,
			int weatherDelay, int nasDelay, int securityDelay,
			int lateAircraftDelay) {
		this.dayOfWeek = dayOfWeek;
		this.flightDate = flightDate;
		this.flightMonth = flightMonth;
		this.flightYear = flightYear;
		this.uniqueCarrier = uniqueCarrier;
		this.flightNum = flightNum;
		this.originAirportId = originAirportId;
		this.destAirportId = destAirportId;
		this.crsDepTime = crsDepTime;
		this.depTime = depTime;
		this.depDelay = depDelay;
		this.crsArrTime = crsArrTime;
		this.arrTime = arrTime;
		this.arrDelay = arrDelay;
		this.cancelled = cancelled;
		this.crsElapsedTime = crsElapsedTime;
		this.actualElapsedTime = actualElapsedTime;
		this.distance = distance;
		this.carrierDelay = carrierDelay;
		this.weatherDelay = weatherDelay;
		this.nasDelay = nasDelay;
		this.securityDelay = securityDelay;
		this.lateAircraftDelay = lateAircraftDelay;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public int getFlightDate() {
		return flightDate;
	}

	public int getFlightMonth() {
		return flightMonth;
	}

	public int getFlightYear() {
		return flightYear;
	}

	public int getFlightNum() {
		return flightNum;
	}

	public int getOriginAirportId() {
		return originAirportId;
	}

	public int getDestAirportId() {
		return destAirportId;
	}

	public int getCrsDepTime() {
		return crsDepTime;
	}

	public int getDepTime() {
		return depTime;
	}

	public int getDepDelay() {
		return depDelay;
	}

	public int getCrsArrTime() {
		return crsArrTime;
	}

	public int getArrTime() {
		return arrTime;
	}

	public int getArrDelay() {
		return arrDelay;
	}

	public int getCancelled() {
		return cancelled;
	}

	public int getCrsElapsedTime() {
		return crsElapsedTime;
	}

	public int getActualElapsedTime() {
		return actualElapsedTime;
	}

	public int getDistance() {
		return distance;
	}

	public int getCarrierDelay() {
		return carrierDelay;
	}

	public int getWeatherDelay() {
		return weatherDelay;
	}

	public int getNasDelay() {
		return nasDelay;
	}

	public int getSecurityDelay() {
		return securityDelay;
	}

	public int getLateAircraftDelay() {
		return lateAircraftDelay;
	}

	public String getUniqueCarrier() {
		return uniqueCarrier;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getFlightDate()).append("|");
		sb.append(getFlightMonth()).append("|");
		sb.append(getFlightYear()).append("|");
		sb.append(getUniqueCarrier()).append("|");
		sb.append(getFlightNum()).append("|");

		return sb.toString();
	}

	public Object getInfo(int index) {
		switch (index) {
		case DAY_OF_WEEK_INDEX:
			return dayOfWeek;
		case FLIGHT_DATE_INDEX:
			return flightDate;
		case FLIGHT_MONTH_INDEX:
			return flightMonth;
		case FLIGHT_YEAR_INDEX:
			return flightYear;
		case UNIQUE_CARRIER_INDEX:
			return uniqueCarrier;
		case FLIGHT_NUM:
			return flightNum;
		case ORIGIN_AIRPORT_ID:
			return originAirportId;
		case DEST_AIRPORT_ID:
			return destAirportId;
		case CRS_DEP_TIME:
			return crsDepTime;
		case DEP_TIME:
			return depTime;
		case DEP_DELAY:
			return depDelay;
		case CRS_ARR_TIME:
			return crsArrTime;
		case ARR_TIME:
			return arrTime;
		case ARR_DELAY:
			return arrDelay;
		case CANCELLED:
			return cancelled;
		case CARRIER_DELAY:
			return carrierDelay;
		case WEATHER_DELAY:
			return weatherDelay;
		case NAS_DELAY:
			return nasDelay;
		case SECURITY_DELAY:
			return securityDelay;
		case LATE_AIRCRAFT_DELAY:
			return lateAircraftDelay;
		default:
			return null;
		}
	}

}
