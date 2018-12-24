import java.io.IOException;
import java.util.Scanner;

public class AppTimeMain {
	private static Scanner in = new Scanner(System.in);

	public static void pause() {
		System.out.print("\nНажмите любую клавишу для продолжения...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printMenu() {
		System.out.println("Выберите действие:");
		System.out.println("1 - Вывод списка маршрутов");
		System.out.println("2 - Добавить маршрут");
		System.out.println("3 - Найти самый дешевый маршрут");
		System.out.println("4 - Найти самый быстрый маршрут");
		System.out.println("5 - Открыть из файла(routes.bin)");
		System.out.println("6 - Сохранить в файл(routes.bin)");
		System.out.println("0 - Выход");
	}

	public static void main(String[] args) {
		printMenu();
		Timetable timetable = new Timetable();

		menu: for (;;) {
			int select = in.nextInt();
			switch (select) {
			case 1:
				System.out.println(timetable);
				break;
			case 2:
				timetable.add(Route.input(in));
				break;
			case 3:
				System.out.print("Введите названия двух станций: ");
				System.out.println(timetable.findCheapRoute(in.next(),
						in.next()));
				break;
			case 4:
				System.out.print("Введите названия двух станций: ");
				System.out.println(timetable.findFastRoute(in.next(), in.next()));
				break;
			case 5:
				timetable.openFromFile("routes.bin");
				System.out.println(timetable);
				break;
			case 6:
				timetable.saveToFile("routes.bin");
				break;
			default:
				break menu;
			}
			pause();
			printMenu();
		}
		// ...
	}
}