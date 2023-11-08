package chapter01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonServiceImpl implements PersonService {

	private String name;
	private int age;
	

	
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("이름: " + name);
		System.out.println("나이: " + age);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public PersonServiceImpl() {
	}
	public PersonServiceImpl(String name) {
		this.name = name;
	}
	public PersonServiceImpl(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
