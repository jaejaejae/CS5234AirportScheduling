package flight.scheduler.util;

public class TimeUtil {

	public static int addTime(int time, int addMinute) {
		int minute = time % 100;
		int hour = time / 100;
		minute += addMinute;
		if (minute > 60) {
			hour += minute / 60;
			minute %= 60;
		}
		return hour * 100 + minute;
	}

	public static int subtractTime(int time, int subtractMinute) {
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
