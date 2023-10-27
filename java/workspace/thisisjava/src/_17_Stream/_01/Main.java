package _17_Stream._01;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Main{
	public static void main(String[]args) {
		Set<String> set = new HashSet<>();
		set.add("홍길동");
		set.add("김길동");
		set.add("고길동");
		
		
//		Stream<String> stream = set.stream();
//		stream.forEach(name -> System.out.println(name));
		
		set.stream().forEach(name -> System.out.println(name));
	}
}