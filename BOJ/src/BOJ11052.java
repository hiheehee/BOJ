import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int card[] = new int[n+1];
		
		for(int i = 1; i < n+1; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		int dp[] = new int[n+1];
		
		for(int i = 1; i < card.length; i++) {
			for(int j = 0; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j]+card[j]);
			}
		}
		
		System.out.println(dp[n]);

	}

}
