import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11724 {
	static int result = 0;
	static ArrayList<Integer>[] al;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		al = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			al[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken())-1;
			int v2 = Integer.parseInt(st.nextToken())-1;
			
			al[v1].add(v2);
			al[v2].add(v1);
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				bfs(i);
			}
		}
		
		System.out.println(result);
	}
	
	static void bfs(int now) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(now);
		visited[now] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(Integer next: al[cur]) {
				if(!visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
			}
		}
		
		result++;
		return;
	}

}