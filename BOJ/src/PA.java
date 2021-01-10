import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PA {
	static int n;
	static String str[];
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		str = br.readLine().split(" ");
		boolean visited[] = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			visited[i] = true;
			endingString(1, str[i], visited, str[i]);
			visited[i] = false;
		}

		System.out.println(result);

	}
	
	static void endingString(int count, String cur, boolean visited[], String s) {
		if(n <= count) {
			boolean check = true;
			for(int i = 0; i < n; i++) {
				if(!visited[i]) {
					check = false;
					break;
				}
			}
			
			if(check) {
				
				result = 1;
			}
			//System.out.println(s);
			return;
		}
		
		if(result == 1) {
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i] && cur.charAt(cur.length()-1) == str[i].charAt(0)) {
				visited[i] = true;
				endingString(count+1, str[i], visited, s+" "+str[i]);
				visited[i] = false;
			}
		}
	}

}
