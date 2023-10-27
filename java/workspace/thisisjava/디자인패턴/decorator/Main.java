package decorator;

public class Main {
	public static void main(String[] args) {
		Basic ame = new Americano(1, 2000);
		System.out.println(ame.getPrice());
		
		Basic ess = new Esspresso(ame, 2, 1000);
		System.out.println(ess.getPrice());
	}
}
