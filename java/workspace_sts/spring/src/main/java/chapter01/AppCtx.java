package chapter01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자바 설정방식은 반드시 어노테이션을 Configuration을 쳐야한다.
@Configuration
public class AppCtx {
	
	// 이 코드가
	/*
	 xml의
	 <bean id="personService" class="chapter01.PersonServiceImpl">
		<property name="name">
			<value>홍길동</value>
		</property>
	</bean>
	
	위 코드랑 똑같은 코드이다. 
	 
	 * */
	@Bean
	public PersonService personService() { // 여기의 메소드명이 xml의 id역할을 하는 것이다.
		PersonService ps = new PersonServiceImpl();
		ps.setName("홍길동");
		return ps;
	}
	
	@Bean
	public PersonService personService2() {
		PersonService ps = new PersonServiceImpl("홍길동");
		return ps;
	}
}
