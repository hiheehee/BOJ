import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {
	static int n; // 도시 개수
	static int m; // 버스 개수
	static int start;
	static int end;
	static ArrayList<path>[] al;
	static int distance[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		al = new ArrayList[n+1];
		distance = new int[n+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i = 0; i < n+1; i++) {
			al[i] = new ArrayList<>();
		}
		
		// 간선 입력  (a, b, c): a -> b 로 가는 가중치 c을 가지는 간선
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			al[a].add(new path(b, c));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra();
		
		System.out.println(distance[end]);
		
	}
	
	static void dijkstra() {
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
