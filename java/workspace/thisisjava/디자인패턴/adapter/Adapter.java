package adapter;

public class Adapter implements Electric110v{
	private Electric220v e220v;
	
	public Adapter(Electric220v e) {
		e220v = e;
	}
	public void on() {
		e220v.poserOn();
	}
}
