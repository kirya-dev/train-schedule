import java.io.Serializable;
import java.time.Duration;

public class Station implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private int minutes;
	private int cost;

	public Station(String name, int minutes, int cost) {
		this.name = name;
		this.minutes = minutes;
		this.cost = cost;
	}

	@Override
	public String toString() {
		if(cost == 0 || minutes == 0)
			return name;
		return name + " (Цена: " + cost + ", Время: " + new TimeDuration(minutes) + ")";
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Station) obj).getName())
				&& minutes == ((Station) obj).getMinutes()
				&& cost == ((Station) obj).getCost();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}