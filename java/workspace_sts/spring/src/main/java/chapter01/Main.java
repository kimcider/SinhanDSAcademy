package chapter01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import chapter02.MemberServiceImpl;

/* 지금은 톰캣에서 실행하는 것이 아니기 때문에 Main메소드에서 factory를 선언해서 컨테이너를 실행하는것. 톰캣을 실행하면 person.xml에 선언된 모든 빈들이 자동으로 생성된다. */
public class Main {
	public static void main(String[] args) {
		// 얘도 있는데 잘 안쓴다.
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/main/java/chapter01/person.xml"));
		
		//보통 이거로 많이쓴다.
		/* person.xml 컨테이너에 접근할 수 있는 변수를 받아온다. */
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("chapter01/person.xml"); 
		
		/* container에서 bean을 id로 꺼내서 사용한다 */
		/* 이때 id는 xml에 작성한 bean의 id이다. */
		PersonService person = (PersonService)factory.getBean("personService");
		person.sayHello();
		
		/* 같은 id로 또 꺼내면 싱글톤이기에 같은 객체가 반환된다. */
		PersonService person2 = (PersonService)factory.getBean("personService");
		System.out.println(person == person2); //둘을 비교해보면 완전 같은 객체이다. 
		
		/* factory.getBean()은 Object를 리턴한다. */
		
		// 이렇게 사용하면 당연히 에러가 난다.
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
