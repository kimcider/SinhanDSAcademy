package _17_Stream._03_streamPipeLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Student{
	String name;
	int score;
}

public class Main {
	public static void main(String[]args) {
		List<Student> list = Arrays.asList(
				new Student("홍길동", 10),
				new Student("김길동", 20),
				new Student("최길동", 30)
		);
		
		// 방법1
		/* 
		Stream<Student> studentStream = list.stream();
		IntStream scoreStream = studentStream.mapToInt(student -> student.score);
		double avg = scoreStream.average().getAsDouble();
		*/
		
		// 방법2
		double avg = 
				list.stream()
				.mapToInt(student->student.score)
				.average()
				.getAsDouble();
		
		System.out.println("평점: " + avg);
	}
}
