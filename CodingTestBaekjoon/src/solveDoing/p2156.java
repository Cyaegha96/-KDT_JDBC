package solveDoing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 포도주 시식
public class p2156 {
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] wine = new int[N];
		
		for(int i=1;i<=N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N];
		int count=1;
		dp[1] = wine[1];
		
		for(int i=2;i<=N;i++) {
			//
			
		
		
		}
	}
}
