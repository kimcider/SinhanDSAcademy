package observer;

public class Button {
	private ButtonListener buttonListener;
	private String name;
	
	public Button(String name) {
		this.name = name;
	}
	
	// 리스너 주입
	public void addListener(ButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}
	
	// 이벤트 발생
	public void click(String msg) {
		buttonListener.clickEvent(msg);
	}
}