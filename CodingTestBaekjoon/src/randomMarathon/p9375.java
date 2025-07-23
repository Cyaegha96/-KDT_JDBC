package randomMarathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

// 패션왕 신혜빈 조합문제
public class p9375 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder= new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0;i<T;i++) {

			int n = Integer.parseInt(br.readLine());
			Set<Integer> valueSet;
			Map<String, Integer> map = new HashMap();
			
			for(int j=0;j<n;j++) {
				
				StringTokenizer tokenizer = new StringTokenizer(br.readLine());
				tokenizer.nextToken();
				String keyString = tokenizer.nextToken();
				map.put(keyString, map.getOrDefault(keyString,0)+1);
		
			}
			int result = 1;
			for(String key:map.keySet()) {
				result *=(map.get(key)+1);
			}
			result-=1;
			
			
			builder.append(result+"\n");
			
		}
		System.out.println(builder);
	}
}
