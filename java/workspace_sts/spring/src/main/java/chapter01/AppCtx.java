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
		<!-- PersonServiceImpl의 필드명이 name이니까. 프로퍼티의 name도 name이어야한다. -->
		<property name="name">
			<value>홍길동</value> <!-- 이렇게 적으면 PersonServiceImpl의 name필드의 값이 홍길동이된다... -->
		</property>
	</bean>
	
	위 코드랑 똑같은 코드이다. 
	 
	 * */
	@Bean
	public PersonService personService() { // xml의 id를 메소드명으로 사용하면 된다. ㅇㅎ!!!
		PersonService ps = new PersonServiceImpl();
		ps.setName("홍길동");
		return ps;
	}
	

}
