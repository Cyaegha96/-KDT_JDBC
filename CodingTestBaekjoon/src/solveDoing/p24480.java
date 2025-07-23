
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//깊이 우선 탐색2
public class p24480 {
	
static int count = 1;
	
	public static void dfs(List<List<Integer>> graph, int start,int[] visited) {
		visited[start] = count++;
		
		List<Integer> edge = graph.get(start);
		Collections.sort(edge);
		Collections.reverse(edge);
		for(int vertex : edge) {
			if(visited[vertex] <1) {
				dfs(graph, vertex, visited);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		int R = Integer.parseInt(tokenizer.nextToken());
		
		List<List<Integer>> graph = new ArrayList<>();
		int[] visited = new int[N+1];
		Arrays.fill(visited, 0);
		
		//그래프 정의
		for(int i=0;i<=N;i++) {
			
			graph.add(new ArrayList<Integer>());
		}
		
		
		for(int i=0;i<M;i++) {
			tokenizer = new StringTokenizer(br.readLine());
			int node= Integer.parseInt(tokenizer.nextToken());
			int node2 = Integer.parseInt(tokenizer.nextToken());
 			graph.get(node).add(node2);
 			graph.get(node2).add(node);
		}
		
		//오름차순 정렬
		
		
		

		//dfs
		dfs(graph,R,visited);
		
		for(int i=1;i<=N;i++) {
			
			System.out.println(visited[i]);
		}
	}
}
