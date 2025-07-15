package solveTypeDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//알고리즘 수업 - 피보나치 수 1
public class p24416 {
	
	static int count1 = 0;
	static int count2 = 0;
	
	static long fib(int n) {
		
		if(n==1 || n == 2) {
			count1++;
			return 1;
		}else {
			
			return fib(n-1) +fib(n-2);
		}
		
		
	}
	
	static long fibonacci(int n, long[] arr) {
		
		if(arr[n] != 0) {
			return arr[n];
		}
		
		count2++;
		for(int i=3;i<=n;i++) {
			arr[n] = fibonacci(n-1, arr) + fibonacci(n-2, arr);
		}
		return arr[n];
		
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		fib(n);
		
		long[] arr = new long[n+1];
		arr[1] = arr[2] = 1;
		fibonacci(n,arr);
		
		System.out.println(count1+ " " + count2);
		
		
	}
}
