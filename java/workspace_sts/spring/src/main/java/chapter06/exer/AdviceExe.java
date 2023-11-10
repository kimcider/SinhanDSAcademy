package chapter06.exer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AdviceExe {
	//실행을 하는 대상을 지정하는것
	@Pointcut("execution(public * chapter06.exer..*(..))")
	public void publicTarget() {}; //여기서 메소드는 실행하려고 쓰는게아니라 어노테이션을 쓰기위한것. 
	
	
	@Around("publicTarget()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable{  //joinPoint는 횡단지점
		Object obj = null;
		System.out.println("메소드 실행 전");
		
		long start = System.nanoTime();
		obj = joinPoint.proceed(); //실제로 메소드가 실행되는곳( 팩토리얼이 실행되는곳 ) 
		long end = System.nanoTime();
		
		System.out.println("AOP에서 잰 시간: " + (end - start));
		
		System.out.println("메소드 실행 후");
		return obj;
	}
	
	
	
}
