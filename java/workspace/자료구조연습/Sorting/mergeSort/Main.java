package mergeSort;

import java.util.Arrays;

public class Main {
	static int num = 40;
	static int[] ary = new int[num];
	static int[] answer = new int[num];
	public static void main(String[]args) {
		for(int i = 0; i < num; i++) {
			ary[i] = (int)(Math.random() * 100) + 1;
		}
		
		System.out.println(Arrays.toString(ary));
		
		mergeSort(0, ary.length - 1);
		
		System.out.println(Arrays.toString(answer));
		
	}
	
	static void mergeSort(int left, int right) {
		if(left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}
	}
	static void merge(int left, int mid, int right) {
		int lIndex = left;
		int rIndex = mid + 1;
		int index = left;
		
		while(lIndex <= mid && rIndex <= right) {
			if(ary[lIndex] < ary[rIndex]) {
				answer[index++] = ary[lIndex++];
			}else {
				answer[index++] = ary[rIndex++];
			}
		}
		
		while(lIndex <= mid) {
			answer[index++] = ary[lIndex++];			
		}
		
		while(rIndex <= right) {
			answer[index++] = ary[rIndex++];			
		}
		
		for(int i = left; i <= right; i++) {
			ary[i] = answer[i];
		}
	}
}
