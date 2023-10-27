package adapter;

public class Television implements Electric220v {
	@Override
	public void poserOn() {
		System.out.println("TV On - 220v");
	}
}
