import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1389 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int adj[][] = new int[n+1][n+1];
		int min = Integer.MAX_VALUE;
		int ind = 0;
		
		for(int i = 0; i < n+1; i++) {
			Arrays.fill(adj[i], 500001);
			adj[i][i] = 0;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u][v] = 1;
			adj[v][u] = 1;
		}
		
		for(int k = 1; k < n+1; k++) {
			for(int i = 1; i < n+1; i++) {
				for(int j = 1; j < n+1; j++) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		
		for(int i = 1; i < n+1; i++) {
			int sum = 0;
			for(int j = 1; j < n+1; j++) {
				sum += adj[i][j];
			}
			if(sum < min) {
				min = sum;
				ind = i;
			}
		}
		
		System.out.print(ind);
		
	}

}
