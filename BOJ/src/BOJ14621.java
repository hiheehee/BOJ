import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14621 {
	static boolean visited[];
	static int count = 0;
	static int n;
	static ArrayList<edge>[] graph;
	static long result = 0;
	static PriorityQueue<edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String school[] = new String[n];
		boolean check = false;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			school[i] = st.nextToken();
		}
		
		graph = new ArrayList[n];
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			
			if(!school[a].equals(school[b])) {
				graph[a].add(new edge(b, c));
				graph[b].add(new edge(a, c));
			}
		}
		
		prim();
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				check = true;
				break;
			}
		}
		
		if(check) System.out.println(-1);
		else System.out.println(result);
		
	}
	
	static void prim() {
		pq.add(new edge(0,0));
		
		while(!pq.isEmpty()) {
			edge cur = pq.poll();

			if(visited[cur.node]) {
				continue;
			}
			visited[cur.node] = true;
			result += cur.weight;
			
			for(edge next : graph[cur.node]) {
				if(!visited[next.node]) {
					pq.offer(next);
				}
			}
			
			if(++count == n) {
				break;
			}
		}
	}
	
	static class edge implements Comparable<edge> {
		int node;
		int weight;
		
		edge(int node, int weight){
			this.node = node;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(edge o) {
			return weight - o.weight;
		}
		
	}

}
