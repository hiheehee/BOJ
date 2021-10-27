import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {

	static ArrayList<Integer> al[];
	static int V, E;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); // 정점의 수
		E = Integer.parseInt(st.nextToken()); // 간선의 수
		int s = Integer.parseInt(st.nextToken()); // 시작 노드
		
		al = new ArrayList[V+1];
		
		for(int i = 0; i < V+1; i++) {
			al[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			al[a].add(b);
			al[b].add(a);
		}
		
		for(int i = 0; i < V+1; i++) {
			Collections.sort(al[i]);
		}
		
		visited = new boolean[V+1];
		dfs(s);
		System.out.println();
		bfs(s);
		
	}
	
	static void bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited = new boolean[V+1];
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(visited[cur]) continue;
			visited[cur] = true;
			System.out.print(cur+" ");
			for(int i : al[cur]) {
				q.add(i);
			}
		}
		
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		System.out.print(cur+" ");
		for(int i : al[cur]) {
			if(!visited[i]) {
				dfs(i);
			}
		}
	}

}
