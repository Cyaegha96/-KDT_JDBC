package solveTypeDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

//신나는 함수 실행
public class p9184 {
	
	static int[][][] arr = new int[21][21][21];
	
	static int w(int a, int b, int c) {
		
		if(a<=0 || b<=0 || c<=0) {
			return 1;
		}
		
		if(a > 20 || b > 20 || c > 20) {
			return w(20, 20, 20);
		}
		
		if(arr[a][b][c] != 0) {
			return arr[a][b][c];
		}
		
		
		if(a < b  &&  b < c) {
			arr[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) -w(a, b-1, c);
		}else {
			arr[a][b][c] =  w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1); 
		}
		return arr[a][b][c];
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			String lineString = br.readLine();
			StringTokenizer st = new StringTokenizer(lineString);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==-1 && b==-1 && c==-1) {
				break;
			}
			
			System.out.printf("w(%d, %d, %d) = %d\n", a,b,c,w(a,b,c));
			
		}
		
		
		
		
		
	}
}
