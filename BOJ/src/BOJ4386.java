import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4386 {
	static boolean visited[];
	static int count = 0;
	static int n;
	static ArrayList<edge>[] graph;
	static double result = 0;
	static PriorityQueue<edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		double star[][] = new double[n][2];
		graph = new ArrayList[n];
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				double x = star[i][0] - star[j][0];
				double y = star[i][1] - star[j][1];
				double len = Math.sqrt((Math.pow(x, 2)+(Math.pow(y, 2))));
				graph[i].add(new edge(j, len));
				graph[j].add(new edge(i, len));
			}
		}

		prim();
		System.out.format("%.2f",result);
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
		double weight;
		
		edge(int node, double weight){
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
