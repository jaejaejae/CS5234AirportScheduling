package flight.scheduler.util;

import flight.data.DelayType;
import flight.data.FlightInformation;
import flight.data.FlightType;
import flight.scheduler.GateInterval;

public class FlightToGateInterval {
	FlightInformation flightInfo;
	FlightType flightType;
	DelayType delayType;
	int delayTime;

	public FlightToGateInterval(FlightInformation flightInfo,
			FlightType flightType, DelayType delayType, int delayTime) {
		this.flightInfo = flightInfo;
		this.flightType = flightType;
		this.delayTime = delayTime;
		this.delayType = delayType;
	}

	public GateInterval getGateInterval() {
		GateInterval gateInterval = new GateInterval(flightInfo, flightType,
				delayType, delayTime);
		return gateInterval;
	}
}
