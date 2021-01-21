import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647 {
	static int parent[];
	static int result;
	static ArrayList<Integer> al = new ArrayList<>();
	static PriorityQueue<edge> pq = new PriorityQueue<>();
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 집의 수 == 정점 개수
		int m = Integer.parseInt(st.nextToken()); // 길의 수 == 간선 개수
		parent = new int[n+1];
		
		for(int i = 0; i < n+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Kruskal();
		
		Collections.sort(al);
		System.out.println(result-al.get(al.size()-1));
		
	}
 	
 	static int find(int x) {
 		if(x == parent[x]) return x;
 		return parent[x] = find(parent[x]);
 	}
 	
 	static void union(int x, int y) {
 		int xRoot = find(x);
 		int yRoot = find(y);
 		
 		if(xRoot != yRoot) {
 			if(x < y) parent[yRoot] = x;
 			else parent[xRoot] = y;
 		}
 	}

 	static void Kruskal() {
 		while(!pq.isEmpty()) {
 			edge cur = pq.poll();
 			
 			int a = find(cur.s);
 			int b = find(cur.e);
 			
 			if(a == b) continue;
 			union(a, b);
 			result += cur.cost;
 			al.add(cur.cost);
 		}
 	}
 	static class edge implements Comparable<edge>{
 		int s;
 		int e;
 		int cost;
 		
 		edge(int s, int e, int cost) {
 			this.s = s;
 			this.e = e;
 			this.cost = cost;
 		}
 		
 		// 집를 연결하는데 드는 비용을 오름차순으로 정렬
 		@Override
 		public int compareTo(edge o) {
 			return cost - o.cost;
 		}
 	}
}
