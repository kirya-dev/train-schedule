import java.util.Comparator;

public class ComprCost implements Comparator<Route> {
	
	@Override
	public int compare(Route o1, Route o2) {
		return o1.getCost() - o2.getCost();
	}
}