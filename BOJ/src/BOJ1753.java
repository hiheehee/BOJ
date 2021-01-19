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
		v = Integer.parseInt(st.nextToken()); // ������ ����
		e = Integer.parseInt(st.nextToken()); // ������ ����
		int k = Integer.parseInt(br.readLine()); // ���� ����
		distance = new int[v+1];
		edge = new ArrayList[v+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i = 0; i < v+1; i++) {
			edge[i] = new ArrayList<>(); 
		}
		
		// ���� �Է�  (start, end, weight): start -> end �� ���� ����ġ weight�� ������ ����
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
		distance[start] = 0; // ���� ������ distance = 0
		
		while(!pq.isEmpty()) {
			path cur = pq.poll();
			
			if(visited[cur.e]) { // �̹� �湮���� �ִ� �����̸� pass
				continue;
			}
			
			// �ƴ϶�� ���� �湮�Ѱ��̹Ƿ� true�� ����
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
		int e; // ����
		int w; // ����ġ
		
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
