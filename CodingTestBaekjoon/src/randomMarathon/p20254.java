package randomMarathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p20254 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int UR = Integer.parseInt(tokenizer.nextToken());
		int TR = Integer.parseInt(tokenizer.nextToken());
		int UO = Integer.parseInt(tokenizer.nextToken());
		int TO = Integer.parseInt(tokenizer.nextToken());
		
		int result = UR * 56 + TR * 24 + UO * 14 + TO*6;
		System.out.println(result);
	}
}
