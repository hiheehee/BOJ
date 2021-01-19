import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238 {
	static int n;
	static int m;
	static int x;
	static ArrayList<path>[] al;
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		al = new ArrayList[n+1];
		int result = 0;
		
		for(int i = 0; i < n+1; i++) {
			al[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			al[s].add(new path(e, w));
		}
		
		for(int i = 1; i < n+1; i++) {
			int sum = dijkstra(i, x);
			sum += dijkstra(x, i);
			
			result = Math.max(result, sum);
		}
		
		System.out.print(result);
	}
	
	static int dijkstra(int start, int index) {
		PriorityQueue<path> pq = new PriorityQueue<>();
		pq.offer(new path(start, 0));
		int distance[] = new int[n+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		
		while(!pq.isEmpty()) {
			path cur = pq.poll();
			
			for(path next : al[cur.node]) {
				if(distance[cur.node] + next.weight < distance[next.node]) {
					distance[next.node] = distance[cur.node] + next.weight;
					pq.offer(new path(next.node, distance[next.node]));
				}
			}
		}
		
		return distance[index];
	
	}

	static class path implements Comparable<path>{
		int node;
		int weight;
		
		path(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(path o) {
			return this.weight - o.weight;
		}
	}
}
