package observer;

public class Main {
	public static void main(String[] args) {
		Button b = new Button("버튼");
		b.addListener(new ButtonListener() {
			
			@Override
			public void clickEvent(String e) {
				System.out.println(e + "페이지로 이동");
			}
		});
		b.click("등록");
		b.click("수정");
		
		b.addListener(new ButtonListener() {
			
			@Override
			public void clickEvent(String e) {
				System.out.println(e + "처리");
			}
		});
		b.click("삭제");
	}
}