package _17_Stream._04_getStreamFromCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
class Product{
	private int pno;
	private String name;
	private String company;
	private int price;
}

public class Main {
	public static void main(String[]args) {
		List<Product> list = new ArrayList<>();
		for(int i = 1; i <= 5; i++) {
			Product pd = new Product(i, "상품" + i, "멋진 회사 ", (int)(Math.random()*10000));
			list.add(pd);
		}
		
		Stream<Product> stream = list.stream();
		stream.forEach(pd -> System.out.println(pd.toString()));
	}
}
