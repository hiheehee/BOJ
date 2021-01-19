import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504 {
	static int n;
	static int e;
	static ArrayList<path>[] al;
	static int distance[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 정점의 개수
		e = Integer.parseInt(st.nextToken()); // 간선의 개수
		al = new ArrayList[n+1];
		int v1;
		int v2;
		long firstpath = 0;
		long secondpath = 0;
		
		distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i = 0; i < n+1; i++) {
			al[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			al[a].add(new path(b, c));
			al[b].add(new path(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		dijkstra(1);
		firstpath += distance[v1];
		secondpath += distance[v2];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		dijkstra(v1);
		firstpath += distance[v2];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		dijkstra(v2);
		secondpath += distance[v1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		dijkstra(v2);
		firstpath += distance[n];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		dijkstra(v1);
		secondpath += distance[n];
		
		if(Integer.MAX_VALUE <= firstpath && Integer.MAX_VALUE <= secondpath) {
			System.out.println(-1);
		}else {
			long min = Math.min(firstpath, secondpath);
			System.out.println(min);
		}
		
		
	}
	
	static void dijkstra(int start) {
		PriorityQueue<path> pq = new PriorityQueue<>();
		pq.offer(new path(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			path cur = pq.poll();
			
			for(path next: al[cur.n]) {
				if(distance[cur.n] + next.w < distance[next.n]) {
					distance[next.n] = distance[cur.n] + next.w;
					pq.offer(new path(next.n, distance[next.n]));
				}
			}
		}
		
	}

	static class path implements Comparable<path>{
		int n; // 정점
		int w; // 가중치
		
		path(int n, int w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(path o) { // 가중치를 오름차순으로 정렬
			return this.w - o.w;
		}
	}
}
