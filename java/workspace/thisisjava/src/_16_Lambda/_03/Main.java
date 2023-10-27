package _16_Lambda._03;

public class Main {
	public static void main(String[]args) {
		Person person = new Person();
		person.action((x, y) -> x+y);
		
		person.action((s, y) -> sum(s, y));
	}
	
	public static double sum(double x, double y) {
		return x + y;
	}
}
