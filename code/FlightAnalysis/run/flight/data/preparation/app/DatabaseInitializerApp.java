package flight.data.preparation.app;

import java.io.File;
import java.io.IOException;

import flight.data.preparation.DatabaseInitializer;
import flight.data.repository.SqliteFlightRepository;

public class DatabaseInitializerApp {
	public static void main(String[] args) {
		String filename = "data/flight012014.csv";
		SqliteFlightRepository repository = new SqliteFlightRepository();
		repository.clear();
		repository.setAutoCommit(false);
		File csvFile = new File(filename);
		DatabaseInitializer databaseInit = new DatabaseInitializer(csvFile,
				repository);
		try {
			databaseInit.begin();
		} catch (IOException e) {
			e.printStackTrace();
		}
		repository.commit();
		repository.setAutoCommit(true);
	}
}
