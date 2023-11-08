package chapter02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDAO memberDAO() { //메소드명이 빈이름이기떄문에 xml에선언된빈이랑 똑같은이름을쓴것
		return new MemberDAOImpl();
	}
	
	@Bean
	public MemberService memberService() {
		MemberServiceImpl m = new MemberServiceImpl();
		
		//memberDAO()객체는 이미 빈에서 생성되어있고, 
		//이렇게 생성되어있는애를 호출해서 사용하는것임
		//자바에서는 이런식으로 DI를 수행한다.
		m.setMemberDAO(memberDAO());
		return m;
	}
	
}
