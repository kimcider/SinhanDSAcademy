package strategy;

public class Main {
	public static void main(String[] args) {
		Encoder encoder = new Encoder();
		encoder.setEncodingStrategy(new Utf8Strategy());
		
		String msg = encoder.getMessage("ㅎㅇ");
		System.out.println(msg);
		
		encoder.setEncodingStrategy(new EuckrStrategy());
		msg = encoder.getMessage(msg);
		System.out.println(msg);
	}
}