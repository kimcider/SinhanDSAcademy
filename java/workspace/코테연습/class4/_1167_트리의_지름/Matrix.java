package _1167_트리의_지름;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 이거 matrix로만들어서 이렇구만.
 * adjacent로마들어야할듯
 * 
 */
public class Matrix {
	static int[][] matrix = null;
	static int numberOfVertex = 0;
	static BufferedReader br = null;
	static int max = 0;
	static boolean[] visited = null;
	public static void main(String[]args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		numberOfVertex = Integer.valueOf(br.readLine());
		buildTree();
		visited = new boolean[numberOfVertex];
		for(int i = 0; i < numberOfVertex; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				DFS(i, 0, 1);
			}
		}
		System.out.println(max);
	}
	
	static void DFS(int start, int val,  int depth) {
		if(depth == numberOfVertex - 1 ) return;
		
		for(int i = 0; i < numberOfVertex; i++) {
			if((i != start) && (visited[i] == false) && (matrix[start][i] != 0)) {
				if(matrix[i][start] + val > max) max = matrix[i][start] + val;
				visited[i] = true;
				DFS(i, matrix[i][start] + val,  depth + 1);
			}
		}
	}
	
	
	static void buildTree() throws Exception{
		matrix = new int[numberOfVertex][numberOfVertex];
		
		for(int i = 0; i < numberOfVertex; i++) {
			String[] line = br.readLine().split(" ");
			int index = 1;
			while(Integer.valueOf(line[index]) != -1) {
				int toVertex = Integer.valueOf(line[index++]);
				int length = Integer.valueOf(line[index++]);
				
				matrix[toVertex - 1][i] = length;
				matrix[i][toVertex - 1] = length;
			}
		}
	}
	
	static void printTree() {
		for(int i = 0; i < numberOfVertex; i++) {
			for(int j = 0; j < numberOfVertex; j++) {
				System.out.printf("%3d ", matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
