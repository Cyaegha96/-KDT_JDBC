package solvingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import java.util.StringTokenizer;




//7562번 나이트의 이동
public class p7562 {
	
	public static class Pair{
		int x;
		int y;
		
		public Pair() {};
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs(int[][] graph, int startx,int starty, int endx, int endy ) {
		graph[startx][starty] = 0;
		
		Deque<Pair> queue = new ArrayDeque<>();
		
		queue.add(new Pair(startx,starty));
		
		while(!queue.isEmpty()) {
			
			Pair s = queue.poll();
			
			if(s.x == endx && s.y == endy) {
				return;
			}
			
			Pair[] position = new Pair[8];
			//나이트의 다음 포지션
			position[0] = new Pair(s.x+1, s.y-2);
			position[1] = new Pair(s.x+2, s.y-1);
			position[2] = new Pair(s.x+2, s.y+1);
			position[3] = new Pair(s.x+1,s.y+2);
			position[4] = new Pair(s.x-1, s.y+2);
			position[5] = new Pair(s.x-2, s.y+1);
			position[6] = new Pair(s.x-2, s.y-1);
			position[7] = new Pair(s.x-1, s.y-2);
			
			for(Pair p: position) {
				if(p.x >= 0 && p.x <graph.length && p.y >=0 && p.y <graph.length) {
					if(graph[p.x][p.y] ==-1) {
						queue.add(p);
						graph[p.x][p.y] = graph[s.x][s.y]+1;
					}
				}
			}
			
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 0;testCase<T;testCase++) {
			
			int max = Integer.parseInt(br.readLine());
			
			int[][] graph = new int[max][max];
			for(int i=0;i<max;i++) {
				Arrays.fill(graph[i], -1);
			}
			
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(tokenizer.nextToken());
			int startY = Integer.parseInt(tokenizer.nextToken());
			
			
			tokenizer = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(tokenizer.nextToken());
			int endY = Integer.parseInt(tokenizer.nextToken());
			
			bfs(graph, startX, startY, endX, endY);
			builder.append(graph[endX][endY]+"\n");
		}
		
		System.out.println(builder);
	}
}
