import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			int m = Integer.parseInt(br.readLine());
			int dp[][] = new int[2][m+2];
			for(int j = 0; j < 2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int k = 2; k < m+2; k++) {
					dp[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int j = 2; j < m+2; j++) {
				dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + dp[0][j];
				dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + dp[1][j];
			}
			
			System.out.println(Math.max(dp[0][m+1], dp[1][m+1]));
		}
		
    }
}