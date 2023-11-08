package chapter01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
	private String name;
	private int age;
	
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("이름: " + name);
		System.out.println("나이: " + age);
	}
	
	public PersonServiceImpl(String name) {
		this.name = name;
	}
}
