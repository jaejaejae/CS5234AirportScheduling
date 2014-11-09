package flight.scheduler;

import flight.data.FlightInformation;

public class CustomGateInterval extends GateInterval {
	public CustomGateInterval(FlightInformation flightInfo,
			int customStartTime, int customEndTime) {
		super(flightInfo, null, null, 0);

		this.customEndTime = customEndTime;
		this.customStartTime = customStartTime;
	}

	int customStartTime;
	int customEndTime;

	@Override
	protected void computeEndTime() {
	};

	@Override
	protected void computeStartTime() {
	};

	@Override
	public int getCrsStartTime() {
		return customStartTime;
	}

	@Override
	public int getCrsEndTime() {
		return customEndTime;
	}

	@Override
	public int getDelayEndTime() {
		return customEndTime;
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", getCrsStartTime(), getCrsEndTime());
	}
}
