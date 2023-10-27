package strategy;


public class Encoder {
	private EncodingStrategy encodingStrategy;
	
	public void setEncodingStrategy(EncodingStrategy encodingStrategy) {
		this.encodingStrategy = encodingStrategy;
	}
	
	public String getMessage(String msg) {
		return this.encodingStrategy.encode(msg);
	}
}