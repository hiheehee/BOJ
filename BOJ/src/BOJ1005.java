import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1005 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			ArrayList<Integer>[] al = new ArrayList[n+1];
			int indegree[] = new int[n+1];
			int cost[] = new int[n+1];
			
			for(int j = 0; j < n+1; j++) {
				al[j] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < n+1; j++) {
				cost[j] = Integer.parseInt(st.nextToken());
			}
			
			
			for(int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				
				al[v1].add(v2);
				indegree[v2]++;
				
			}
			
			int destination = Integer.parseInt(br.readLine());
			
			System.out.println(topologicalSort(indegree, al, cost, destination));
		}
		

	}
	
	static int topologicalSort(int indegree[], ArrayList<Integer>[] al, int cost[], int destination) {
		Queue<Integer> q = new LinkedList<>();
		int result[] = new int[indegree.length];
		
		for(int i = 1; i < indegree.length; i++) {
			result[i] = cost[i];
			
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			for(Integer i : al[node]) {
				indegree[i]--;
				result[i] = Math.max(result[i], result[node] + cost[i]);
				
				if(indegree[i] == 0) {
					q.offer(i);
				}
			}
			
		}
		return result[destination];
	}

}
