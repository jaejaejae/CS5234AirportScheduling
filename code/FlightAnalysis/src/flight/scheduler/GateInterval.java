package flight.scheduler;

import flight.data.DelayType;
import flight.data.FlightInformation;
import flight.data.FlightType;

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

	private void computeEndTime() {
		crsEndTime = 0;
		switch (flightType) {
		case Arrival:
			crsEndTime = flightInfo.getCrsArrTime();
			break;
		case Departure:
			crsEndTime = flightInfo.getCrsDepTime();
			break;
		}
		crsEndTime = addTime(crsEndTime, flightType.getPostTime());
	}

	private void computeStartTime() {
		crsStartTime = 0;
		switch (flightType) {
		case Arrival:
			crsStartTime = flightInfo.getCrsArrTime();
			break;
		case Departure:
			crsStartTime = flightInfo.getCrsDepTime();
			break;
		}

		crsStartTime = subtractTime(crsStartTime, flightType.getPriorTime());
	}

	public int getCrsStartTime() {
		return crsStartTime;
	}

	public int getCrsEndTime() {
		return crsEndTime;
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", getCrsStartTime(), getCrsEndTime());
	}

	public int addTime(int time, int addMinute) {
		int minute = time % 100;
		int hour = time / 100;
		minute += addMinute;
		if (minute > 60) {
			hour += minute / 60;
			minute %= 60;
		}
		return hour * 100 + minute;
	}

	public int subtractTime(int time, int subtractMinute) {
		int minute = time % 100;
		int hour = time / 100;
		minute -= subtractMinute;
		while (minute < 0) {
			hour--;
			minute += 60;
		}
		return hour * 100 + minute;
	}
}
