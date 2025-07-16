package randomMarathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p18301 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(tokenizer.nextToken());
		int n2 = Integer.parseInt(tokenizer.nextToken());
		int n3 = Integer.parseInt(tokenizer.nextToken());
	
		
		int result = (n1+1) * (n2+1) / (n3 +1) - 1;
		System.out.println(result);
	}
}
