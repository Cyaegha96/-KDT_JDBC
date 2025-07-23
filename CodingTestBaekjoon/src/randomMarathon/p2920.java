package randomMarathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2920 {
	public static void main(String[] args) throws IOException {
		String ascending = "1 2 3 4 5 6 7 8";
		String descending  = "8 7 6 5 4 3 2 1";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputString = br.readLine();
		
		if(inputString.equals(ascending)) {
			System.out.println("ascending");
		}else if(inputString.equals(descending)) {
			System.out.println("descending");
		}else {
			System.out.println("mixed");
		}
		
		
	}
}
