import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1197 {
	static int parent[];
	static int result;
	static PriorityQueue<edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken()); // 정점 개수
		int e = Integer.parseInt(st.nextToken()); // 간선 개수
		parent = new int[v+1];
		
		for(int i = 0; i < v+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Kruskal();
		System.out.println(result);
		
	}
	
	static void Kruskal() {
		while(!pq.isEmpty()) {
			edge cur = pq.poll();
			
			int a = find(cur.start);
			int b = find(cur.end);
			
			if(a == b) {
				continue;
			}
			union(a,b);
			result += cur.weight;
			
		}
	}
	
	static int find(int x) {
		if(x == parent[x]) {
			return x;
		}else {
			return parent[x] = find(parent[x]);
		}
	}
	
	static void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		// 부모가 같지 않을때 더 작은값으로 넣어줌
		if(xRoot != yRoot) {
			if(x < y) parent[yRoot] = x;
            else parent[xRoot] = y;
		}
	}
	
	static class edge implements Comparable<edge>{
		int start;
		int end;
		int weight;
		
		edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		// 가중치를 오름차순으로 정렬
		@Override
		public int compareTo(edge o) {
			return weight - o.weight;
		}
	}

}
