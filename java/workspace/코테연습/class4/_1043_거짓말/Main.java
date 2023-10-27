package _1043_거짓말;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n = Integer.valueOf(line[0]);
		int m = Integer.valueOf(line[1]);
		
		//n번째 사람은 list[n]에 넣자. list[0]은 사용하지 말자.
		boolean[] list = new boolean[n + 1];
		
		line = br.readLine().split(" ");
		for(int i = 1; i < line.length; i++) {
			list[Integer.valueOf(line[i])] = true;
		}
		
		int[][] ary = new int[m][];
		for(int i = 0; i < m; i++) {
			line = br.readLine().split(" ");
			int num = Integer.valueOf(line[0]);
			ary[i] = new int[num];
			for(int j = 0; j < ary[i].length; j++) {
				ary[i][j] = Integer.valueOf(line[j + 1]);
			}
		}
		
		boolean change = true;
		while(change == true) {
			change = false;
			for(int i = 0; i < m; i++) {
				boolean checkIs = false;
				for(int j = 0; j < ary[i].length; j++) {
					for(int k = 1; k <= n; k++) {
						if(ary[i][j] == k && list[k] == true) {
							checkIs = true;
							break;
						}
					}
					if(checkIs == true) {
						for(int k = 0; k < ary[i].length; k++) {
							if(list[ary[i][k]] == false) {
								list[ary[i][k]] = true;
								change = true;
							}
						}
						break;
					}
				}
			}
		}

		
		int answer = 0;
		for(int i = 0; i < m; i++) {
			boolean whoKnows = false;
			for(int j = 0; j < ary[i].length; j++) {
				if(list[ary[i][j]] == true) {
					whoKnows = true;
					break;
				}
			}
			if(whoKnows == false) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}
