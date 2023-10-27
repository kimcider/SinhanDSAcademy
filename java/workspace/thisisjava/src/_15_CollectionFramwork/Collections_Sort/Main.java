package _15_CollectionFramwork.Collections_Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import _15_CollectionFramwork.Comparator.Fruit;
import lombok.Getter;

@Getter
public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		
		list.add(32);
		list.add(223);
		list.add(31);
		list.add(13);
		list.add(23);
		
		Collections.sort(list);
		System.out.println(list);
		Collections.sort(list, Collections.reverseOrder());
		System.out.println(list);
		
		
		
		
		
		List<Fruit> set = new ArrayList<>();
		set.add(new Fruit("포도", 3000));
		set.add(new Fruit("수박", 10000));
		set.add(new Fruit("딸기", 6000));
		
		Collections.sort(set, (o1, o2) -> o1.price - o2.price);
		
		Collections.sort(set, (o1, o2) -> {System.out.println(o1 + ", "  +o2);return o1.price - o2.price;});
//		Collections.sort(set, new Comparator<Fruit>() {
//			@Override
//			public int compare(Fruit a, Fruit b) {
//				return a.price - b.price;
//			}
//		});
		System.out.println(set);
	}
}
