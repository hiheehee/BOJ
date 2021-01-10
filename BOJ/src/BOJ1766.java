import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1766 {
	static StringBuilder sb = new StringBuilder();
	static int indegree[];
	static ArrayList<Integer>[] al;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		al = new ArrayList[n+1];
		indegree = new int[n+1];
		
		for(int i = 0; i < n+1; i++) {
			al[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			al[v1].add(v2);
			indegree[v2]++;
		}
		
		topologicalSort();
		
		System.out.println(sb.toString());
		
	}
	
	static void topologicalSort() {
		PriorityQueue<Integer> q = new PriorityQueue<>();

		for(int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()){
			int node = q.poll();
			sb.append(node+" ");

			for(Integer i : al[node]) {
				indegree[i]--;
				
				if(indegree[i] == 0) {
					q.offer(i);
				}
			}
		}
		
		return;
	}

}