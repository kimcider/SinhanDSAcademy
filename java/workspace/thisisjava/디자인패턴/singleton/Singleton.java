package singleton;

public class Singleton {
	// private 접근 권한을 갖는 static 필드 선언과 초기화
	// 프로그램이 실행될 때 메모리의 static영역에 단 한개의 Singleton 객체를 저장.
	private static Singleton singleton = new Singleton();
	
	// private이기에 다른 클래스에서 Singleton 객체 생성 불가
	private Singleton() {}
	
	// Singleton객체 생성 없이 getInstance()에 접근할 수 있어야하기에 static으로 선언.
	public static Singleton getInstance() {
		return singleton;
	}
}