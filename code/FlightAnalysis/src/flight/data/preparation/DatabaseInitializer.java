package flight.data.preparation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import flight.data.FlightInformation;
import flight.data.repository.FlightRepository;

public class DatabaseInitializer {
	File csvFile;
	BufferedReader br;
	String csvSplitBy = ",";
	String line;
	String[] contents;
	FlightRepository repository;

	public DatabaseInitializer(File csvFile, FlightRepository repository) {
		this.csvFile = csvFile;
		this.repository = repository;
	}

	// 0 DAY_OF_WEEK FL_DATE UNIQUE_CARRIER FL_NUM ORIGIN_AIRPORT_ID
	// 5 DEST_AIRPORT_ID CRS_DEP_TIME DEP_TIME DEP_DELAY CRS_ARR_TIME ARR_TIME
	// 11 ARR_DELAY CANCELLED CRS_ELAPSED_TIME ACTUAL_ELAPSED_TIME DISTANCE
	// 16 CARRIER_DELAY WEATHER_DELAY NAS_DELAY SECURITY_DELAY
	// LATE_AIRCRAFT_DELAY
	public void begin() throws IOException {
		br = new BufferedReader(new FileReader(csvFile));
		line = br.readLine(); // skip header;
		while ((line = br.readLine()) != null) {
			contents = line.split(csvSplitBy);
			FlightInformation info = extract();
			repository.insert(info);
		}
	}

	private FlightInformation extract() {
		int dayOfWeek = getInt(0);

		String[] flightDateParam = contents[1].split("-");
		int flightDate = Integer.parseInt(flightDateParam[0]);
		int flightMonth = Integer.parseInt(flightDateParam[1]);
		int flightYear = Integer.parseInt(flightDateParam[2]);

		String uniqueCarrier = contents[2];
		int flightNum = getInt(3);
		int originAirportId = getInt(4);
		int destAirportId = getInt(5);
		int crsDepTime = getInt(6);
		int depTime = getInt(7);
		int depDelay = getInt(8);
		int crsArrTime = getInt(9);
		int arrTime = getInt(10);
		int arrDelay = getInt(11);
		int cancelled = getInt(12);
		int crsElapsedTime = getInt(13);
		int actualElapsedTime = getInt(14);
		int distance = getInt(15);
		int carrierDelay = getInt(16);
		int weatherDelay = getInt(17);
		int nasDelay = getInt(18);
		int securityDelay = getInt(19);
		int lateAircraftDelay = getInt(20);

		FlightInformation info = new FlightInformation(dayOfWeek, flightDate,
				flightMonth, flightYear, uniqueCarrier, flightNum,
				originAirportId, destAirportId, crsDepTime, depTime, depDelay,
				crsArrTime, arrTime, arrDelay, cancelled, crsElapsedTime,
				actualElapsedTime, distance, carrierDelay, weatherDelay,
				nasDelay, securityDelay, lateAircraftDelay);

		return info;
	}

	private int getInt(int i) {
		if (i >= contents.length)
			return 0;
		String content = contents[i].replaceAll("\"", "");
		if (content.length() == 0)
			return 0;
		double d = Double.parseDouble(content);
		return (int) d;
	}
}
