package flight.data;

public class FlightInformation {

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
}
