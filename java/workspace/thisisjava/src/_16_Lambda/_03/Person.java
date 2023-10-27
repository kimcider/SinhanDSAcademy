package _16_Lambda._03;

public class Person {
	public void action(Calculable cal) {
		double result = cal.calc(10, 4);
		System.out.println("결과: " + result);
	}
}
