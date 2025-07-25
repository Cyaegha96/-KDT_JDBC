package solveSubtotal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 두 수의 합
// 투포인트 알고리즘을 적용해 풀어야 함
public class p3273 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		
		for(int i=0;i<n;i++) {
			arr[i] =Integer.parseInt(tokenizer.nextToken());
		}
		
		int target =Integer.parseInt(br.readLine());
		int result = 0;
		int startPoint = 0;
		int endPoint = n-1;
		
		Arrays.sort(arr);
		
		while(startPoint<endPoint) {
			if(arr[startPoint] + arr[endPoint] > target) {
				endPoint--;
			}else if(arr[startPoint] + arr[endPoint] <target) {
				startPoint++;
			}else if(arr[startPoint] + arr[endPoint] == target) {
				result++;
				startPoint++;
				endPoint--;
			}
			
		}
		System.out.println(result);
		
	}
	
	
}
