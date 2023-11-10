package chapter06.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chapter06.exer.AdviceExe;
import chapter06.exer.Calculator;
import chapter06.exer.CalculatorImpl;
import chapter06.exer.CalculatorImpl2;

@Configuration
@EnableAspectJAutoProxy //프록시를 활성화시키기 위해서. 
public class AppCtx {
	
	//이렇게 하면 컬큘레이터 등록 끝!
	@Bean
	public Calculator calculator() {
		return new CalculatorImpl();
	}
	
	
	@Bean
	public Calculator calculator2() {
		return new CalculatorImpl2();
	}
	@Bean
	public AdviceExe adviceExe() {
		return new AdviceExe();
	}
}
