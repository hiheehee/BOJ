import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21918 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int bulb[] = new int[n];
		st = new StringTokenizer(br.readLine());
		
		
		for(int i = 0; i < n; i++) {
			bulb[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				bulb[b] = c;
			}else if(a == 2) {
				for(int j = b; j < c; j++) {
					if(bulb[j] == 1) bulb[j] = 0;
					else bulb[j] = 1;
				}
			}else if(a == 3) {
				for(int j = b; j < c; j++) {
					bulb[j] = 0;
				}
			}else {
				for(int j = b; j < c; j++) {
					bulb[j] = 1;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.print(bulb[i]+" ");
		}
	}

}
