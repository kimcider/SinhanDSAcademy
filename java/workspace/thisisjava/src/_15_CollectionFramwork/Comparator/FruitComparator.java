package _15_CollectionFramwork.Comparator;

import java.util.Comparator;

public class FruitComparator implements Comparator<Fruit>{
	@Override
	public int compare(Fruit a, Fruit b) {
		return a.price - b.price;
	}
}
