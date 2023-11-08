package chapter01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import chapter02.MemberServiceImpl;

public class Main {
	public static void main(String[] args) {
		// xml(빈 설정파일)을 읽어들임. 
		//모든 빈 객체를 생성시켜 빈 컨테이너에 담아둔다.
		// 스프링이 설정되어 있는 빈 객체를 생성시켜서 context에 저장한다. 
		// (이게제일중요한듯)지금은 톰캣이 아니니까 직접 읽어들이는거고, 실제로는 톰캣을 실행하면 person.xml에 있는 빈들이 자동으로 생성되는 것이다
		// 보통 빈펙토리 잘 안쓰고
//		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/main/java/chapter01/person.xml"));
		
		//보통 이거로 많이쓴다.
		//얘는 이제 주소가 클래스패스부터 시작하니 경로를 다시 바꿔준다.
		/*
		 * 클래스패스xml쓸때  spring/target/classes. 

			D:\java\workspace_sts\spring\target\classes파일들이 다 여기로들어온다.
			
			src에 만들지만 파일들이 다 여기로온다. 그래서 클래스패스가 여기가된다.
		 * */
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("chapter01/person.xml"); 
		
		
		
		//personService는 person.xml에 작성한 bean의 id.
		//이러헥 하면 person에는 객체가 담긴다.
		
		// container에서 꺼낸다. id로 ㅇㅇ.
		PersonService person = (PersonService)factory.getBean("personService");
		person.sayHello();
		
		//이미 저장되어있는놈을 꺼내기에 
		PersonService person2 = (PersonService)factory.getBean("personService");
		//둘을 비교해보면 완전 같은 객체이다. 
		System.out.println(person == person2);

		//이렇게하면 에러나고
//		PersonService person3 = factory.getBean("personService"); 
		//이런식으로 작성하면 에러가 안난다. 
		PersonService person3 = factory.getBean("personService", PersonService.class); 
		
		//매개변수 1짜리 생성자
		//스프링이 new PersonServiceImpl("이순신")으로 만들어둔 것을 꺼내주는것. 
		//ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("chapter01/person.xml"); 
		//위 코드를 실행할 떄 xml에 있는 모든 객체들을 생성시키는것. 
		PersonService service1 = factory.getBean("personService1", PersonService.class);
		service1.sayHello();
		
		//매개변수 2짜리 생성자
		//스프링이 new PersonServiceImpl("손흥민", 23)으로 만들어둔 것을 꺼내주는것. 
		PersonService service2 = factory.getBean("personService2", PersonService.class);
		service2.sayHello();
		
	}
}
