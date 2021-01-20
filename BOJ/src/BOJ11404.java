import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11404 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int adj[][] = new int[n+1][n+1];
		
		for(int i = 0; i < n+1; i++) {
			Arrays.fill(adj[i], 10000001);
			adj[i][i] = 0;
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u][v] = Math.min(adj[u][v], w);
		}
		
		for(int k = 1; k < n+1; k++) {
			for(int i = 1; i < n+1; i++) {
				for(int j = 1; j < n+1; j++) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		
		
		for(int i = 1; i < n+1; i++) {
			for(int j = 1; j < n+1; j++) {
				if(adj[i][j] >= 10000001) {
					System.out.print(0+" ");
				}else {
					System.out.print(adj[i][j]+" ");
				}
			}System.out.println();
		}
	}

}
