package solveSubtotal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2559 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(tokenizer.nextToken());
		int K = Integer.parseInt(tokenizer.nextToken());
		
		int[] arr = new int[N+1];
		tokenizer = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=N;i++) {
			arr[i] =Integer.parseInt(tokenizer.nextToken());
		}
		int max = -100 * 100000;
		for(int i=1;i<=N+1-K;i++){
			int sum =0;
			for(int j=i;j<i+K;j++) {
				sum += arr[j];
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
		
		
	}
}
