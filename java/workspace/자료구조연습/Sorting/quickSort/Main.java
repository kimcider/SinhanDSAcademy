package quickSort;

import java.util.Arrays;

public class Main {
	static int num = 40;
	static int[] ary = new int[num];
	public static void main(String[]args) {
		for(int i = 0; i < num; i++) {
			ary[i] = (int)(Math.random() * 100) + 1;
		}
		
		System.out.println(Arrays.toString(ary));
		
		quickSort(0, ary.length - 1);
		
		System.out.println(Arrays.toString(ary));
		
	}
	
	static void quickSort(int left, int right) {
		if(left < right) {
			int pivot = partition(left, right);
			quickSort(left, pivot - 1);
			quickSort(pivot + 1, right);
		}
	}
	static int partition(int left, int right) {
		int x = ary[right];
		int pos = left;
		for(int i = left; i < right; i++) {
			if(ary[i] < x) {
				swap(pos, i);
				pos = pos + 1;
			}
		}
		swap(pos, right);
		return pos;
	}
	static void swap(int i, int j) {
		int temp = ary[i];
		ary[i] = ary[j];
		ary[j] = temp;
	}
}