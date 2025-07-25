package solvingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class p1697 {
	
	public static void bfs(int[] graph, int start, int end) {
		graph[start]=0;
		
		Deque<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		
		while(!queue.isEmpty()) {
			
			start = queue.poll();
			
			if(start == end) {
				return;
			}
			
			int position1 = start+1;
			int position2 = start-1;
			int position3 = start *2;
			
			if (position1 < graph.length && graph[position1] == 0) {
			    queue.add(position1);
			    graph[position1] = graph[start] + 1;
			}

			if(position2 >=0 && graph[position2] == 0) {
				queue.add(position2);
				graph[position2] = graph[start]+1;
		
			}
			if (position3 < graph.length && graph[position3] == 0) {
			    queue.add(position3);
			    graph[position3] = graph[start] + 1;
			}
				
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int N= Integer.parseInt(tokenizer.nextToken());
		int K = Integer.parseInt(tokenizer.nextToken());
		
		int[] graph = new int[100001];
		bfs(graph, N,K);
		
		System.out.println(graph[K]);
		
	}
}
