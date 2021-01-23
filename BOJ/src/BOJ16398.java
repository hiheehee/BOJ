import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398 {
	static long result;
	static boolean visited[];
	static ArrayList<edge>[] graph;
	static int count;
	static int n;
	static PriorityQueue<edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n];
		graph = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int w = Integer.parseInt(st.nextToken());
				graph[i].add(new edge(j, w));
			}
		}
		
		prim();
		System.out.println(result);

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
					pq.add(next);
				}
			}
			
			if(++count == n) {
				break;
			}
		}
	}
	
	static class edge implements Comparable<edge>{
		int node;
		int weight;
		
		edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		// 가중치를 오름차순으로 정렬
		@Override
		public int compareTo(edge o) {
			return weight - o.weight;
		}
	}
	
}
