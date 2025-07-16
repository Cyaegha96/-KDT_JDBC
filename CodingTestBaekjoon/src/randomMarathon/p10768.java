package randomMarathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//특별한 날
public class p10768 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n1 = Integer.parseInt(br.readLine());
		int n2 = Integer.parseInt(br.readLine());
		String result = "";
		if(n1 > 2) {
			result = "After";
		}else if(n1 == 2 && n2 > 18) {
			result = "After";
		}else if(n1 == 2 && n2 == 18) {
			result = "Special";
		}else {
			result = "Before";
		}
		
		
	
		System.out.println(result);
	}
}
