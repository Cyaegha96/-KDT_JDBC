package solveTypeDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 포도주 시식
public class p2156 {
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] wine = new int[N+1];
		
		for(int i=1;i<=N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N+1];

		
		dp[1] = wine[1];
		
		
		if(N>=2) {
			dp[2] = wine[1] + wine[2];
		}
		if(N>=3) {
			dp[3] = Math.max(wine[1]+wine[2],wine[2]+wine[3]);
			dp[3] = Math.max(dp[3], wine[1] + wine[3]);
			
		}
		
		
	
		for(int i=4;i<=N;i++) {
			
			dp[i]= Math.max( dp[i-1], //이번잔은 건너뛴 경우
					Math.max(dp[i-2]+wine[i], //한번 건너뛰고 이번거 마시는 경우 
							dp[i-3]+wine[i-1] + wine[i]) //저저번까지 마시고, 연속 두잔 마시는 경우
					);
		}
		
		System.out.println(dp[N]);
	}
}
