import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1753 {
	static int v;
	static int e;
	static ArrayList<path>[] edge;
	static int distance[];
	static PriorityQueue<path> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken()); // 정점의 개수
		e = Integer.parseInt(st.nextToken()); // 간선의 개수
		int k = Integer.parseInt(br.readLine()); // 시작 정점
		distance = new int[v+1];
		edge = new ArrayList[v+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i = 0; i < v+1; i++) {
			edge[i] = new ArrayList<>(); 
		}
		
		// 간선 입력  (start, end, weight): start -> end 로 가는 가중치 weight을 가지는 간선
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edge[start].add(new path(end, weight));
		}
		
		Dijkstra(k);
		
		for(int i = 1; i < v+1; i++) {
			if(distance[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(distance[i]);
			}
		}

	}
	
	static void Dijkstra(int start) {
		boolean visited[] = new boolean[v+1];
		pq.offer(new path(start, 0)); 
		distance[start] = 0; // 시작 정점의 distance = 0
		
		while(!pq.isEmpty()) {
			path cur = pq.poll();
			
			if(visited[cur.e]) { // 이미 방문한적 있는 정점이면 pass
				continue;
			}
			
			// 아니라면 현재 방문한것이므로 true로 변경
			visited[cur.e] = true;
			
			for(path next: edge[cur.e]) {
				if(distance[cur.e] + next.w < distance[next.e]) {
					distance[next.e] = distance[cur.e] + next.w;
					pq.offer(new path(next.e, distance[next.e]));
				}
			}
			
		}
	}
	
	static class path implements Comparable<path>{
		int e; // 정점
		int w; // 가중치
		
		path(int e, int w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(path o) {
			return this.w - o.w;
		}
	}

}
