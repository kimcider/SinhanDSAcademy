package adapter;

public class Radio implements Electric110v {
	@Override
	public void on() {
		System.out.println("라디오 On - 110v");
	}
}
