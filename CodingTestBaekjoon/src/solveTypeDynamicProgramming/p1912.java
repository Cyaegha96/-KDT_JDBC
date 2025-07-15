package solveTypeDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//연속합
public class p1912 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		
		int[] arr = new int [n+1];
		int[] dp = new int[n+1];
		
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=n;i++) {
			arr[i] = Integer.parseInt(tokenizer.nextToken());
			dp[i] = arr[i];
		}
		int result = -1001;
		for(int i=1;i<=n;i++) {
			
			dp[i] = Math.max(dp[i-1]+arr[i], dp[i]);
			result= Math.max(result, dp[i]);
		}
		System.out.println(result);
		
		
		
	}
}
