import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20530 {
	static int circuit[][];
	static ArrayList<Integer> al[];
	static int result[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		circuit = new int[n][n];
		al = new ArrayList[n];
		result = new int[q];
		
		for(int i = 0; i < n; i++) {
			al[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken())-1;
			int v2 = Integer.parseInt(st.nextToken())-1;
			circuit[v1][v2] = circuit[v2][v1] = 1;
			al[v1].add(v2);
			al[v2].add(v1);
		}
		
		for(int i = 0; i < q; i++) {
			boolean visited[] = new boolean[n];
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			visited[s] = true; 
			dfs(s, e, i, visited);
			System.out.println(result[i]);
		}
		
		
	}

	static void dfs(int start, int end, int ind, boolean[] visited) {
		if(start == end) {
			result[ind]++;
			return;
		}
		
		for(Integer next: al[start]) {
			if(visited[next]) {
				continue;
			}
			visited[next] = true;
			dfs(next, end, ind, visited);
		}

	}
}
