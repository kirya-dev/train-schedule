import java.util.Comparator;

public class ComprTime implements Comparator<Route> {

	@Override
	public int compare(Route o1, Route o2) {
		return o1.getDuration() - o2.getDuration();
	}
}