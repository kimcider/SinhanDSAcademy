package _15_CollectionFramwork.Comparable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person implements Comparable<Person>{
	public String name;
	public int age;
	
	@Override
	public int compareTo(Person o) {
		if(age < o.age)return -1;
		else if(age == o.age)return 0;
		else return 1;
	}	
}
