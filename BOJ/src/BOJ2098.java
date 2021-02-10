import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2098 {
	static int dp[][];
	static int map[][];
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][(1<<n)-1];
		
		for(int i = 0; i < n; i++) {
			String num[] = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(num[j]);
			}
		}

		for(int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(TravelingSalesman(0, 1));
		
	}
	
	static int  TravelingSalesman(int cur, int visited) {
		// 모든 지점을 방문한 경우
		if(visited == (1<<n)-1) {
			if(map[cur][0] == 0) return 987654321;
			return map[cur][0];	
		}	
		
		// 방문한 적이 있는 경우
		if(dp[cur][visited] >= 0) return dp[cur][visited]; 
		
		int min = 987654321;
		for(int i = 0; i < n; i++) {
			// 방문한 적이 없고 길이 존재할 경우
			if((visited & (1<<i)) == 0 && map[cur][i] != 0) {
				int dis = TravelingSalesman(i, visited | (1<<i)) + map[cur][i];
				min = Math.min(min, dis);
			}
		}
		dp[cur][visited] = min;
		return dp[cur][visited];
	}

}
