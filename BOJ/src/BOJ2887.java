import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2887 {
	static boolean visited[];
	static int count = 0;
	static int n;
	static ArrayList<edge>[] graph;
	static long result = 0;
	static PriorityQueue<edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		long planet[][] = new long[n][3];
		graph = new ArrayList[n];
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			planet[i][0] = Integer.parseInt(st.nextToken());
			planet[i][1] = Integer.parseInt(st.nextToken());
			planet[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(planet, new Comparator<long []>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return o1[0] < o2[0] ? -1 : 1;
			}
			
		});
		
		
		for(int i = 1; i < n; i++) {
			long x = planet[i-1][0] - planet[i][0];
			long y = planet[i-1][1] - planet[i][1];
			long z = planet[i-1][2] - planet[i][2];
			long min = Math.min(Math.abs(x), Math.abs(y));
			min = Math.min(Math.abs(z), min);
			graph[i].add(new edge(i-1, min));
			graph[i-1].add(new edge(i, min));
		}

		Arrays.sort(planet, new Comparator<long []>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return o1[1] < o2[1] ? -1 : 1;
			}
			
		});
		
		
		for(int i = 1; i < n; i++) {
			long x = planet[i-1][0] - planet[i][0];
			long y = planet[i-1][1] - planet[i][1];
			long z = planet[i-1][2] - planet[i][2];
			long min = Math.min(Math.abs(x), Math.abs(y));
			min = Math.min(Math.abs(z), min);
			graph[i].add(new edge(i-1, min));
			graph[i-1].add(new edge(i, min));
		}
		
		Arrays.sort(planet, new Comparator<long []>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return o1[2] < o2[2] ? -1 : 1;
			}
			
		});
		
		
		for(int i = 1; i < n; i++) {
			long x = planet[i-1][0] - planet[i][0];
			long y = planet[i-1][1] - planet[i][1];
			long z = planet[i-1][2] - planet[i][2];
			long min = Math.min(Math.abs(x), Math.abs(y));
			min = Math.min(Math.abs(z), min);
			graph[i].add(new edge(i-1, min));
			graph[i-1].add(new edge(i, min));
		}
		
		prim();
		System.out.print(result);
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

	static class edge implements Comparable<edge>{
		int node;
		long weight;
		
		edge(int node, long weight){
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(edge o) {
			if(this.weight < o.weight) {
				return -1;
			}else {
				return 1;
			}
			
		}
		
		
	}
}