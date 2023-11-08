package chapter03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDAO memberDAO() {
		return new MemberDAOImpl();
	}
	
	
	@Bean
	public MemberService memberService() {
		MemberServiceImpl m = new MemberServiceImpl();
		
		//이제 m.setMemberDAO(memberDAO()); 얘를 안쓰겠다는것!!!
		//얘를 주입을 안해주면 지금 당장은 널포인터 익셉션이 뜬다.
		
		//MemberServiceImpl에 가서 
		//@Autowire를 memberDAO위에 넣어주면 
		//아래를 주입할 필요 없이 알아서 주입을 해준다.
//		m.setMemberDAO(memberDAO());
		return m;
	}
	
	
	@Bean
	public MemberService memberService2() {
		//MemberServiceImpl에 가서 
		//@Autowire를 memberDAO위에 넣어주면 
		//이제 이 안에도 주입할 필요가 없다.
//		return new MemberServiceImpl(memberDAO());
		return new MemberServiceImpl();
	}
	
	//Autowire시 같은 클래스의 객체가 두개있을경우 어떻게 될지 체크용.
	//memberServiceImpl.java로가자!
	@Bean
	public MemberDAO adminDAO() {
		return new AdminDAOImpl();
	}
}
