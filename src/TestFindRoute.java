import org.junit.*;

public class TestFindRoute {

	private Timetable timetable;

	@Before
	public void setUp() throws Exception {

		Route r1 = new Route("Москва", new TimeDuration(1, 2, 3));
		r1.addStation(new Station("Ростов", 60 * 10, 2000));

		timetable = new Timetable();
		timetable.add(r1);

		Route r2 = new Route("Ростов", new TimeDuration(2, 1, 3));
		r2.addStation(new Station("Москва", 60 * 15, 1500));
		timetable.add(r2);
	}

	@Test
	public void testFindCheapRoute() {
		String expected = timetable.findCheapRoute("Ростов", "Москва");

		String actual = "Самый дешевый маршрут будет стоить 1500 и займет 15ч.";

		Assert.assertTrue(expected.equals(actual));
	}

	@Test
	public void testFindFastRoute() {
		String expected = timetable.findFastRoute("Москва", "Ростов");

		String actual = "Самый быстрый маршрут будет стоить 2000 и займет 10ч.";

		Assert.assertTrue(expected.equals(actual));
	}
}