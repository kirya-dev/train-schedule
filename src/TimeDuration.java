import java.io.Serializable;
import java.util.Scanner;

public class TimeDuration implements Serializable {
	private static final long serialVersionUID = 1L;

	private int days;
	private int hours;
	private int minutes;

	private int duration = 0;

	public TimeDuration(int days, int hours, int minutes) {
		setDays(days);
		setHours(hours);
		setMinutes(minutes);
	}

	public TimeDuration(int minutes) {
		setDuration(minutes);
	}

	@Override
	public String toString() {
		return ((days > 0) ? days + "дн." : "")
				+ ((hours > 0) ? hours + "ч." : "")
				+ ((minutes > 0) ? minutes + "мин." : "");
	}

	public void addMinutes(int val) {
		setDuration(getDuration() + val);
	}

	public void addHours(int val) {
		setDuration(getDuration() + 60 * val);
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		duration = 0;
		this.minutes = checkBounds(minutes, 0, 59);
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		duration = 0;
		this.hours = checkBounds(hours, 0, 23);
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		duration = 0;
		this.days = checkBounds(days, 1, 31);
	}

	public int getDuration() {
		if (duration == 0)
			duration = minutes + ((hours + days * 24) * 60);
		return duration;
	}

	public void setDuration(int m) {
		minutes = m % 60;
		int h = m / 60;
		hours = h % 24;
		int d = h / 24;
		days = d;
		duration = m;
	}

	public static int checkBounds(int value, int minValue, int maxValue) {
		try {
			if (minValue > value || value > maxValue)
				throw new Exception("Invalid bounds!");
		} catch (Exception e) {
			System.out.println(e + " Plese Enter from " + minValue + " to " + maxValue);
			value = checkBounds((new Scanner(System.in).nextInt()), minValue, maxValue);
		}
		return value;
	}
}