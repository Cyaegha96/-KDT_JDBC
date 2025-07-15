package solveTypeDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//01타일
public class p1904_implement {
	
	static List<Set<String>> arr = new ArrayList<>();
	
	static public Set<String> zeroOne(int n) {
		
		if(n <1) return arr.get(0);
		else if(n==1) return arr.get(1);
		else if(n==2) return arr.get(2); 
		else if(arr.size() > n) {
			return arr.get(n);
		}
		else {
			//2칸 전은 00만 붙입니다.
			Set<String> set= zeroOne(n-2);
			Set<String> buffer= new HashSet<>();
			
			for(String s : set) {
				buffer.add(s+"00");
				buffer.add("00"+s);
			}
			
			//1칸 전은 1만 붙입니다.
			
			Set<String> set2= zeroOne(n-1);
			for(String s : set2) {
				buffer.add(s+"1");
				buffer.add("1"+s);
			}
			arr.add(n, buffer);
			
			return arr.get(n);
		}
	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		Set<String> set= new HashSet<>();
		
		arr.add(0,set);
		
		Set<String> set2= new HashSet<>();
		
		set2.add("1");
		arr.add(1,set2);
		
		Set<String> set3= new HashSet<>();
		
		set3.add("00");
		set3.add("11");
		
		arr.add(2,set3);
		
		System.out.println(zeroOne(N).size() % 15746);
		System.out.println(arr.get(N).toString());
		
	}
}
