package flight.scheduler;

import flight.data.DelayType;
import flight.data.FlightInformation;
import flight.data.FlightType;
import flight.scheduler.util.TimeUtil;

public class GateInterval {
	FlightInformation flightInfo;
	FlightType flightType;
	DelayType delayType;
	int delayTime;

	int crsStartTime;
	int crsEndTime;

	public GateInterval(FlightInformation flightInfo, FlightType flightType,
			DelayType delayType, int delayTime) {
		this.flightInfo = flightInfo;
		this.flightType = flightType;
		this.delayType = delayType;
		this.delayTime = delayTime;

		computeStartTime();
		computeEndTime();
	}

	protected void computeEndTime() {
		crsEndTime = 0;
		switch (flightType) {
		case Arrival:
			crsEndTime = flightInfo.getCrsArrTime();
			break;
		case Departure:
			crsEndTime = flightInfo.getCrsDepTime();
			break;
		}
		crsEndTime = TimeUtil.addTime(crsEndTime, flightType.getPostTime());
	}

	protected void computeStartTime() {
		crsStartTime = 0;
		switch (flightType) {
		case Arrival:
			crsStartTime = flightInfo.getCrsArrTime();
			break;
		case Departure:
			crsStartTime = flightInfo.getCrsDepTime();
			break;
		}

		crsStartTime = TimeUtil.subtractTime(crsStartTime,
				flightType.getPriorTime());
	}

	public int getCrsStartTime() {
		return crsStartTime;
	}

	public int getCrsEndTime() {
		return crsEndTime;
	}

	public int getDelayEndTime() {
		switch (flightType) {
		case Arrival:
			if (flightInfo.getArrDelay() <= 0)
				return getCrsEndTime();
			else
				return TimeUtil.addTime(getCrsEndTime(),
						flightInfo.getArrDelay());
		case Departure:
			if (flightInfo.getDepDelay() <= 0)
				return getCrsEndTime();
			else
				return TimeUtil.addTime(getCrsEndTime(),
						flightInfo.getDepDelay());
		}
		return -1;
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", getCrsStartTime(), getCrsEndTime());
	}

	public FlightInformation getFlightInfo() {
		return flightInfo;
	}
}
