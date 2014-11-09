package flight.util;

public class IntegerPair implements Comparable<IntegerPair> {
	int first;
	int second;

	public IntegerPair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	@Override
	public int compareTo(IntegerPair arg0) {
		return new Integer(this.first).compareTo(new Integer(arg0.first));
	}

}
