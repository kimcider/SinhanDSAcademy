package chapter02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDAO memberDAO() { //메소드명이 빈의 id 역할을 한다.
		return new MemberDAOImpl();
	}
	
	//setter방식
	@Bean
	public MemberService memberService() {
		MemberServiceImpl m = new MemberServiceImpl();
		
		//memberDAO()객체는 이미 빈에서 생성되어있고, 
		//이렇게 생성되어있는애를 호출해서 사용하는것임
		//자바에서는 이런식으로 DI를 수행한다.
		m.setMemberDAO(memberDAO());
		return m;
	}
	
	
	//생성자 방식
	@Bean
	public MemberService memberService2() {
		return new MemberServiceImpl(memberDAO());
	}
}
