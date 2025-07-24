import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class solvingGraph {
	
	
	public static List<Integer> queueBFS(int v, Map<Integer, List<Integer>> graph){
		//결과 노드를 저장할 리스트 선언
		List<Integer> discovered = new ArrayList<Integer>();
		//큐 선언은 효율적인 ArrayDeque 사용.
		
		discovered.add(v);
		
		Deque<Integer> queue = new ArrayDeque<Integer>();
	
		queue.add(v);
		
		while(!queue.isEmpty()) {
			
			v = queue.poll();
			
			
			for(int node:graph.get(v)) {
				if(!discovered.contains(node)) {
					discovered.add(node);
					queue.add(node);
				}
			}
			
			
		}
		return discovered;
	
	}
	
	
	public static List<Integer> iterativeDFS(int v, Map<Integer, List<Integer>> graph){
		//결과 노드를 저장할 리스트 선언
		List<Integer> discovered = new ArrayList<Integer>();
		
		//스택 선언은 효율적인 ArrayDeque 사용.
		Deque<Integer> stack = new ArrayDeque<Integer>();
		
		stack.push(v);
		while(!stack.isEmpty()) {
			v= stack.pop();
			if(!discovered.contains(v)) {
				discovered.add(v);
				//현재 노드에서 연결된 모든 주변 노드를 스택에 삽입
				for(int w: graph.get(v)) {
					stack.push(w);
				}
			}
		}
		
		return discovered;
		
		
		
	}
	
	public static List<Integer> recursiveDFS(int v, List<Integer> discoverd, Map<Integer, List<Integer>> graph) {
		 
		//현재 노드 저장
		discoverd.add(v);
		
		//주변 노드 반복.
		for(int node: graph.get(v)) {
			//아직 처리되지 않은 노드라면 깊이 기반 탐색 진행
			if(!discoverd.contains(node)) {
				recursiveDFS(node, discoverd, graph);
			}
		}		
		
		return discoverd;
	}
	
	public static void main(String[] args) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		
		List<Integer> visited = new ArrayList<>();
		
		graph.put(1, Arrays.asList(2,3,4));
		graph.put(2, Arrays.asList(5));
		graph.put(3, Arrays.asList(5));
		graph.put(4, Arrays.asList());
		graph.put(5,  Arrays.asList(6,7));
		graph.put(6, Arrays.asList());
		graph.put(7, Arrays.asList(3));
		
		//dfs 결과 출력- 재귀로 구현
		System.out.println(recursiveDFS(1, visited, graph));
	
		
		//dfs 결과 출력 - 스택으로 구현 (다만 이경우 사전식 순서가 아니라 역순으로 방문)
		System.out.println(iterativeDFS(1, graph));
		
		//bfs 결과 출력
		System.out.println(queueBFS(1, graph));
	}
}
