package chapter01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainByJava {

	public static void main(String[] args) {
		//클래스를 읽어들여서 bean객체를 생성해서 bean container에 저장.
		//Annotation이고, 그 패스가 AppCtx클래스임을 기억해라!!!
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		PersonService personService = (PersonService)ctx.getBean("personService");
		personService.sayHello();
		
		PersonService personService2 = (PersonService)ctx.getBean("personService");
		System.out.println(personService == personService2);
		
		
	}

}
