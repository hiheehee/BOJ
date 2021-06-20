import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10942 {

	static int n;
	static int num[];
	static boolean dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		String str[] = br.readLine().split(" ");
		num = new int[n];
		dp = new boolean[n][n];
		
		
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(str[i]);
		}

		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) { // 팰린드롬 길이 1인것 모두 true 초기화
			dp[i][i] = true;
		}
		
		for(int i = 0; i < n-1; i++) { // 팰린드롬 길이 2인것 앞뒤 같다면 true 초기화
			if(num[i] == num[i+1]) {
				dp[i][i+1] = true;
				dp[i+1][i] = true;
			}
		}
		
		makePalindrome();
		
		while(m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			if(dp[s][e]) {
				bw.write(1+"\n");
			}else {
				bw.write(0+"\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static void makePalindrome() {
		for(int i = 2; i < n; i++) {
			for(int j = 0; j < n-i; j++) {
				if(num[j] == num[j+i] && dp[j+1][j+i-1]) {
					dp[j][j+i] = dp[j+i][j] = true;
				}
			}
		}
	}

}
