package _15_CollectionFramwork.Comparator;

import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[]args) {
		TreeSet<Fruit> set = new TreeSet<>(new FruitComparator());
		set.add(new Fruit("포도", 3000));
		set.add(new Fruit("수박", 10000));
		set.add(new Fruit("딸기", 6000));
//		TreeSet<Fruit> temp = Set.cop
		for(Fruit f : set) {
			System.out.println(f.getName() + " : " + f.getPrice());
		}
	}
}
