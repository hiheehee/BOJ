import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20529 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < n; i++) {
			int t = Integer.parseInt(br.readLine());
			String str[] = br.readLine().split(" ");
			long result = 100001;
			
			for(int a = 0; a < t; a++) {
				for(int b = a+1; b < t; b++) {
					for(int c = b+1; c < t; c++) {
						long Sum = 0;
						
						Sum += checkCount(str[a], str[b]);
						Sum += checkCount(str[b], str[c]);
						Sum += checkCount(str[a], str[c]);
						
						result = Math.min(result, Sum);
						
						if(result == 0) {
							break;
						}
					}
					if(result == 0) {
						break;
					}
					
				}
				if(result == 0) {
					break;
				}
			}

			sb.append(result+"\n");
			
		}
		
		System.out.println(sb.toString());
	
	}

	static int checkCount(String a, String b) {
		int count = 0;
		for(int i = 0; i < 4; i++) {
			if(a.charAt(i) != b.charAt(i)) {
				count++;
			}
		}
		return count;
	}

}