import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922 {
	static int parent[];
	static int result;
	static PriorityQueue<edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // ��ǻ���� �� == ���� ����
		int m = Integer.parseInt(br.readLine()); // ���� ����
		parent = new int[n+1];
		
		for(int i = 0; i < n+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.offer(new edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Kruskal();
		
		System.out.println(result);
	}
	
	static void Kruskal() {
		while(!pq.isEmpty()) {
			edge cur = pq.poll();
			
			int x = find(cur.start);
			int y = find(cur.end);
			
			if(x == y) {
				continue;
			}
			union(x,y);
			result += cur.cost;
		}
	}
	
	static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		// �θ� ���� ������ �� ���������� �־���
		if(xRoot != yRoot) {
			if(x < y) parent[yRoot] = x;
            else parent[xRoot] = y;
		}
	}
	
	static class edge implements Comparable<edge> {
		int start;
		int end;
		int cost;
		
		edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		// ��ǻ�͸� �����ϴµ� ��� ����� ������������ ����
		@Override
		public int compareTo(edge o) {
			return cost - o.cost;
		}
	}

}
