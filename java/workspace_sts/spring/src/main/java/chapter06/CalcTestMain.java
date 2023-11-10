package chapter06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcTestMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("chapter06/AOPTest.xml");
		Calculator cal = context.getBean("proxyCal", Calculator.class);
		
		cal.add(100, 20);
		System.out.println();
	
		cal.subtrace(100, 20);
		System.out.println();
		
		cal.multiply(100, 20);
		System.out.println();
		
		cal.divide(100, 20);
		System.out.println();

		
		// 스프링에서 만든 객체 cal과 내가 직접 만든 객체 cal2는 다르다.
		// 스프링에서 만든 객체에 대해서만 로깅 어드바이스를 붙이는 것이 가능하다!!
		System.out.println(cal.getClass().getName());
		Calculator cal2 = new Calculator();
		System.out.println(cal2.getClass().getName());
	}

}
