package _17_Stream._06_Mapping;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Main {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		list.add(new Student("홍길동", 85));
		list.add(new Student("홍길동", 92));
		list.add(new Student("홍길동", 87));
		
		list.stream()
			.mapToInt(s -> s.getScore())
			.forEach(sc -> System.out.println(sc));
	}

}


@Getter
@Setter
@AllArgsConstructor
class Student{
	String name;
	int score;
}