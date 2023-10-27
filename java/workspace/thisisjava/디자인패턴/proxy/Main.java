package proxy;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

	public static void main(String[] args) {
		Browser b1 = new Chrome("www.naver.com");
		b1.rendering();
		b1.rendering();
		b1.rendering();
		
		Browser b2 = new ChromeCache("www.daum.net");
		b2.rendering();
		b2.rendering();
		b2.rendering();
		
		
//		AOP 사용
		AtomicLong start = new AtomicLong();
		AtomicLong end = new AtomicLong();
		
		Browser b3 = new AOPBrowser("www.google.com", 
				()->{
					System.out.println("시작");
					start.set(System.nanoTime());
				}, 
				() ->{
					System.out.println("끝");
					end.set(System.nanoTime());
				});
		b3.rendering();
		System.out.println(end.get());
		b3.rendering();
		System.out.println(end.get());
		b3.rendering();
		System.out.println(end.get());
		b3.rendering();
		System.out.println(end.get());
	}
}
