package solvingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//유기농 배추
public class p1012 {
	
	public static void dfs(int i, int j, char[][] grid) {
		if(i<0 || i>=grid.length || j<0 || j>= grid[i].length || grid[i][j] == 0) {
			return;
		}
		
		grid[i][j] = 0;
		
		//동서남북 재귀 dfs
		dfs(i, j+1, grid);
		dfs(i, j-1, grid);
		dfs(i+1, j, grid);
		dfs(i-1, j, grid);
		
	}
	
	public static int numIsland(char[][] grid) {
		int count = 0;
		//모든 행렬을 탐색
		
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				 if(grid[i][j] == 1) {
					 dfs(i, j, grid);
					 //재귀 dfs가 끝나면 탐색 한영역 완성
					 count++;
				 }
			}
		}
		return count;
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int  T = Integer.parseInt(br.readLine());
		
		for(int testcase= 0;testcase<T;testcase++) {
	
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(tokenizer.nextToken());
			int N = Integer.parseInt(tokenizer.nextToken());
			int K = Integer.parseInt(tokenizer.nextToken());
			
			char[][] graph = new char[M][N];
			
			for(int i=0;i<K;i++) {
				
				tokenizer = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(tokenizer.nextToken());
				int n= Integer.parseInt(tokenizer.nextToken());
				graph[m][n]= 1;
			}
			
			System.out.println(numIsland(graph));
		}
		
		
	}
}
