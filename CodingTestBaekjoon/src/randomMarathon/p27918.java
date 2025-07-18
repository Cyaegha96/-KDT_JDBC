package randomMarathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//탁구경기
public class p27918 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int X = 0;
		int Y = 0;
		
		for(int i=0;i<N;i++) {
			switch (br.readLine()) {
			case "D":
				X++;
				break;
				
			case "P":
				Y++;
				break;
			}
			if(Math.abs(X-Y) ==2) {
				break;
			}
			
		}
		System.out.println(X+":"+Y);
		
	}
}
