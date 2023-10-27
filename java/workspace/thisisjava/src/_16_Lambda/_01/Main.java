package _16_Lambda._01;

public class Main {

	public static void main(String[] args) {
		action((x, y) -> {
			x = x * 2;
			int result = x + y;
			System.out.println("result: " + result);
		});

		action((x, y) -> {
			int result = x - y;
			System.out.println("result: " + result);
		});
	}

	public static void action(Calculable cal) {
		int x = 10;
		int y = 4;
		cal.calculate(x, y);
	}
}
