package solveDoing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p15667 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println((int) Math.sqrt(N));
		
	}
}
