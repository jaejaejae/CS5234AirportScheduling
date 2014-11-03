package flight.data;

public enum FlightType {
	Departure(90, 30), Arrival(0, 45);

	private int priorTime, postTime;

	FlightType(int priorTime, int postTime) {
		this.priorTime = priorTime;
		this.postTime = postTime;
	}

	public int getPriorTime() {
		return priorTime;
	}

	public int getPostTime() {
		return postTime;
	}
}
