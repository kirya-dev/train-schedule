import java.io.*;
import java.util.ArrayList;

public class Timetable {
	private ArrayList<Route> routes;

	public Timetable() {
		this.routes = new ArrayList<Route>(0);
	}

	@Override
	public String toString() {
		String buf = "Расписание:";
		for (Route route : routes)
			buf += "\n" + route;
		return buf;
	}

	public void add(Route e) {
		this.routes.add(e);
	}

	public String findCheapRoute(String s1, String s2) {
		int duration = 0;
		int minCost = -1;
		for (Route route : routes) {
			int f1 = route.findStation(s1);
			int f2 = route.findStation(s2);
			if (f1 != -1 && f2 != -1) {
				int cost = route.getCost(f1, f2);
				if (minCost == -1 || cost < minCost) {
					minCost = cost;
					duration = route.getDuration(f1, f2);
				}
			}
		}
		if (minCost == -1)
			return "Маршрут не найден.";
		return "Самый дешевый маршрут будет стоить " + minCost
				+ " и займет " + new TimeDuration(duration);
	}

	public String findFastRoute(String s1, String s2) {
		int cost = 0;
		int minDuration = -1;
		for (Route route : routes) {
			int f1 = route.findStation(s1);
			int f2 = route.findStation(s2);
			if (f1 != -1 && f2 != -1) {
				int duration = route.getDuration(f1, f2);
				if (minDuration == -1 || duration < minDuration) {
					minDuration = duration;
					cost = route.getCost(f1, f2);
				}
			}
		}
		if (minDuration == -1)
			return "Маршрут не найден.";
		return "Самый быстрый маршрут будет стоить " + cost
				+ " и займет " + new TimeDuration(minDuration);
	}

	public void saveToFile(String filename) {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream stream = new ObjectOutputStream(file);
			stream.writeObject(this.routes);
			stream.close();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void openFromFile(String filename) {
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream stream = new ObjectInputStream(file);
			this.routes = ((ArrayList<Route>) stream.readObject());
			stream.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}