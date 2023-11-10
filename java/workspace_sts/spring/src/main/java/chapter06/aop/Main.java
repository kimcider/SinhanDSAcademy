package chapter06.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chapter06.exer.Calculator;

public class Main {

	public static void main(String[] args) {
		/* calculatorImpl - for문*/
		//AOP을 사용하지 않은 방식
//		Calculator cal = new CalculatorImpl();
//		cal.factorial(1000);
		
		//AOP를 사용한 방식
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
//		Calculator cal = ctx.getBean("calculator", Calculator.class);
//		cal.factorial(10);
		
		
		/* calculatorImpl2 - 재귀*/
		// AOP를 사용하지 않은 방식
//		CalculatorImpl2 cal = new CalculatorImpl2();
//		Calculator2Exe calExe = new Calculator2Exe(cal);
//		
//		calExe.factorial(100);
		
		// AOP를 사용한 방식
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		Calculator cal = ctx.getBean("calculator2", Calculator.class);
		cal.factorial(10);
		System.out.println(cal.getClass().getName()); //위에 사용한 cal클래스가 내가 만든 그 컬큘레이터가 아니다.
													//스프링이 프록시를 이용해 새로 정의한 클래스를 사용하는것
													//그리고 그 새로 정의한 클래스의 메소드는 AdviceExe의 invoke가 감싸고있어서
													//cal의 메소드를 호출하면 스프링이 만든 새로운 클래스의 함수를 호출해서 start시간을 재고, factorial함수를 호출하고, end시간을 재주는것
													//calculator2Exe로 감싼것처럼 자동으로 감싸주는것
		// 이렇게 한번 만들어두면 여러 클래스, 메소드에 다 적용할 수 있다.
		
	}

}
