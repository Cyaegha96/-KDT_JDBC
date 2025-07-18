package randomMarathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p21633 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		
		double trugiks = k* 0.01d+25;
		if(trugiks > 2000) {
			trugiks = 2000d;
		}else if(trugiks < 100) {
			trugiks = 100d;
		}
		System.out.printf("%.2f",trugiks);
		
	}
}
