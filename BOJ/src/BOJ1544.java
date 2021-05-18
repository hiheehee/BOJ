import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ1544 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean visited[] = new boolean[n];
		String word[] = new String[n];
		HashSet<String> hs = new HashSet<>();
		
		for(int i = 0; i < n; i++) {
			word[i] = br.readLine();
		}
		
		for(int i = 0; i < n; i++) {
			boolean check = false;
			for(int j = 0; j < n; j++) {
				if(i != j && !visited[i]) {
					for(int k = 0; k < word[i].length()*2; k++) {
						String temp = word[i].substring(1)+word[i].charAt(0);
						word[i] = temp;
						if(word[i].equals(word[j])) {
							visited[i] = true;
							visited[j] = true;
							check = true;
							break;
						}
					}
					if(check) {
						break;
					}
				}
			}
		}
		
		for(String s:word) {
			hs.add(s);
		}
		
		System.out.print(hs.size());
	}

}

