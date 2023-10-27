package _15_CollectionFramwork.Comparable;

import java.util.TreeSet;

public class Main {
	public static void main(String[]args) {
		TreeSet<Person> set = new TreeSet<>();
		
		set.add(new Person("홍길동", 45));
		set.add(new Person("김자바", 25));
		set.add(new Person("홍길동", 31));
		
		for(Person person : set) {
			System.out.println(person.name + " : " + person.age);
		}
	}
}
