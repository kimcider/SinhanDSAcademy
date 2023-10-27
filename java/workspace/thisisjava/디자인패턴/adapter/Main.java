package adapter;

public class Main {
	public static void main(String[] args) {
		Radio r = new Radio();
		connect(r);
		
		Television t = new Television();
//		connect(t);
		
		Adapter a = new Adapter(t);
		connect(a);
	}
	
	public static void connect(Electric110v obj) {
		obj.on();
	}
}
