package solveDoing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//조교는 새디스트야!
public class p14656 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[] arr2 = Arrays.copyOf(arr, N);
		for(int i=0;i<N;i++) {
			arr[i]  = Integer.parseInt(tokenizer.nextToken());
			arr2[i] = arr[i];
		}
		
		Arrays.sort(arr);
		int result = 0;
		for(int i=0;i<N;i++) {
			if(arr[i] != arr2[i]) {
				result++;
			}
		}
		System.out.println(result);
		
	}
}
