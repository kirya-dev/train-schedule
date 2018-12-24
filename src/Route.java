import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<Station> stations;
	private TimeDuration timeDispatch;
	private int duration; // Вычислимое поле - время пути (в минутах)
	private int cost; // Вычислимое поле - цена

	public Route(String stationName, TimeDuration timeDispatch) {
		this.stations = new ArrayList<Station>(0);
		this.stations.add(new Station(stationName, 0, 0));
		this.timeDispatch = timeDispatch;
		this.duration = 0;
		this.cost = 0;
	}

	@Override
	public String toString() {
		String buf = "Рейс \"" + stations.get(0).getName() + "\" - \"" + stations.get(stations.size() - 1).getName()
				+ "\"";
		buf += "\n Отправляется: " + timeDispatch;
		buf += "\n Маршрут: " + stations;
		buf += "\n В пути: " + new TimeDuration(getDuration());
		buf += "\n Стоимость: " + getCost();
		return buf;
	}

	public static Route input(Scanner in) {

		System.out.print("Введите название стартовой станции: ");
		String stName = in.next();
		System.out.print("Введите день и время отправления [dd hh mm]: ");
		TimeDuration d = new TimeDuration(in.nextInt(), in.nextInt(), in.nextInt());

		Route newRoute = new Route(stName, d);

		System.out.print("Введите количество следующих станций: ");
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.print("Введите название станции: ");
			stName = in.next();
			System.out.print("Введите продолжительность [hh mm]: ");
			int hh = in.nextInt();
			int mm = in.nextInt();
			System.out.print("Введите стоимость проезда: ");
			int cost = in.nextInt();
			newRoute.addStation(new Station(stName, hh * 60 + mm, cost));
		}
		return newRoute;
	}

	public void addStation(Station e) {
		this.stations.add(e);
	}

	public int getDuration() {
		if (this.duration == 0)
			for (Station station : stations)
				this.duration += station.getMinutes();
		return this.duration;
	}

	public int getCost() {
		if (this.cost == 0)
			for (Station e : stations)
				this.cost += e.getCost();
		return this.cost;
	}

	public int getCost(int ind1, int ind2) {
		int cost = 0;
		int from = Math.min(ind1, ind2) + 1;
		int to = Math.max(ind1, ind2);
		for (int i = from; i <= to; i++)
			cost += stations.get(i).getCost();
		return cost;
	}

	public int getDuration(int ind1, int ind2) {
		int duration = 0;
		int from = Math.min(ind1, ind2) + 1;
		int to = Math.max(ind1, ind2);
		for (int i = from; i <= to; i++)
			duration += stations.get(i).getMinutes();
		return duration;
	}

	public int findStation(String name) {
		for (int i = 0; i < stations.size(); i++)
			if (stations.get(i).getName().equals(name))
				return i;
		return -1;
	}

	@Override
	public boolean equals(Object obj) {
		return stations.equals(((Route) obj).stations);
	}
}