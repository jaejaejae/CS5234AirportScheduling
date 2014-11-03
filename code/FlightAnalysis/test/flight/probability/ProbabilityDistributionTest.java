package flight.probability;

import jdistlib.Weibull;

public class ProbabilityDistributionTest {
	public static void main(String[] args) {
		Weibull poisson = new Weibull(0.7, 20);
		int[] count = new int[100];
		for (int i = 0; i < 100000; i++) {
			int val = (int) poisson.random();
			if (val >= 100)
				val = 99;
			count[val]++;
		}

		for (int i = 0; i < 100; i++) {
			System.out.println(count[i] * 22727 / count[0]);
		}
	}
}
