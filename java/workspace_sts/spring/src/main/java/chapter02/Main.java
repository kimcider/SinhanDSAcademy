package chapter02;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("chapter02/member.xml"); 
		
		MemberService service = ctx.getBean("memberService", MemberServiceImpl.class);
		
		/*
		 * 처음 스타트할 떄 memeber.xml을 생성해서 객체를 생성시켜둔다.
		 * 이때 memberService객체가 생성되기 전에 
		 * */
		service.listMembers();
	}

}
