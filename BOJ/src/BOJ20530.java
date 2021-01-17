import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20530 {
	static ArrayList<Integer> al[];
	static int result[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		al = new ArrayList[n+1];
		result = new int[q];
		
		for(int i = 0; i < n+1; i++) {
			al[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			al[v1].add(v2);
			al[v2].add(v1);
		}
		
		for(int i = 0; i < q; i++) {
			int circuit[][] = new int[n+1][n+1];
			boolean visited[] = new boolean[n+1];
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			visited[s] = true;
			dfs(s, e, i, circuit, visited);
		}
		
		for(int i = 0; i < q; i++) {
			System.out.println(result[i]);
		}
		
	}

	static void dfs(int start, int end, int ind, int circuit[][], boolean visited[]) {
		if(start == end) {
			result[ind]++;
			return;
		}
		
		for(Integer next: al[start]) {
			if(circuit[next][start] != 0 || visited[next]) {
				continue;
			}
			circuit[next][start] = circuit[start][next] = 1;
			visited[next] = true;
			dfs(next, end, ind, circuit, visited);
			circuit[next][start] = circuit[start][next] = 0;
			visited[next] = false;
		}

	}
}
