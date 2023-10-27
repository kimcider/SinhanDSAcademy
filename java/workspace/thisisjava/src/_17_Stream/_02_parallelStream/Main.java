package _17_Stream._02_parallelStream;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Main{
	public static void main(String[]args) {
		List<String> list = new LinkedList<>();
		list.add("홍길동");
		list.add("김길동");
		list.add("고길동");
		
		Stream<String> parallelStrema = list.parallelStream();
		parallelStrema.forEach(name -> {
			System.out.println(name + ": " + Thread.currentThread().getName());
		});
	}
}