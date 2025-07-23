package solveDoing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class p11279 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder= new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			int stat = Integer.parseInt(br.readLine());
			if(stat==0 && pq.isEmpty()) {
				builder.append(0+"\n");
			}else if(stat==0) {
				builder.append(pq.poll()+"\n");
			}else {
				pq.add(stat);
				
			}
		}
		
		System.out.println(builder);
	}
}
