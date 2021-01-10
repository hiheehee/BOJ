import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16168 {
	static int circuit[][];
	static int v;
	static int e;
	static String result = "NO";
	static ArrayList<Integer>[] al;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		al = new ArrayList[v];
		circuit = new int[v][v];
		
		for(int i = 0; i < v; i++) {
			al[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			al[a].add(b);
			al[b].add(a);
		}

		for(int i = 0; i < v; i++) {
			dfs(i, i, 0);
		}
		
		System.out.println(result);
	}
	
	
	static void dfs(int now, int num, int count) {
		if(count == e) {
			result = "YES";
			return;
		}
		for(Integer next: al[now]) {
			if(circuit[now][next] == num || circuit[next][now] == num) {
				continue;
			}
			circuit[now][next] = circuit[next][now] = num;
			dfs(next, num, count+1);
		}
	}

}
