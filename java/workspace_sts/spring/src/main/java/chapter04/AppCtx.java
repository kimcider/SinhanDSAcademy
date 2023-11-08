package chapter04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "chapter04") //chapter04부터 모든 하위 패키지들을 전부 다 뒤진다. 
//componentScan을 안하면 당연히 에러나겠지? 객체들을 생성하지 않을테니까. 
public class AppCtx {
	
}
