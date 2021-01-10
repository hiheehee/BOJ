import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2623 {
	static int indegree[];
	static ArrayList<Integer>[] al;
	static ArrayList<Integer> result = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		indegree = new int[n+1];
		al = new ArrayList[n+1];
		
		for(int i = 0; i < n+1; i++) {
			al[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int v1 = Integer.parseInt(st.nextToken());

			for(int j = 1; j < t; j++) {
				int v2 = Integer.parseInt(st.nextToken());
				al[v1].add(v2);
				indegree[v2]++;
				v1 = v2;
			}
		}
		
		topologicalSort();
		
		if(result.size() == n) {
			for(Integer i : result) {
				System.out.print(i+" ");
			}
		}else {
			System.out.println(0);
		}
		

	}
	
	static void topologicalSort() {
		Queue<Integer> q = new LinkedList<>();

		for(int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()){
			int node = q.poll();
			result.add(node);

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
