package chapter02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainByJava {
	public static void main(String[]args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		MemberService m = ctx.getBean("memberService", MemberService.class);
		m.listMembers();
		
		
	}
}
