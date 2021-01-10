import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ2252 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		ArrayList<Integer>[] arr = new ArrayList[n+1];
		int indegree[] = new int[n+1];
		
		for(int i = 0; i < n+1; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			arr[v1].add(v2);
			indegree[v2]++;
		}
		

		System.out.println(topologicalSort(indegree, arr));
		
	}
	
	static String topologicalSort(int indegree[], ArrayList<Integer>[] arr) {
		Queue<Integer> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()){
			int node = q.poll();
			sb.append(node+" ");
			
			for(Integer i : arr[node]) {
				indegree[i]--;
				
				if(indegree[i] == 0) {
					q.offer(i);
				}
			}
		}
		
		return sb.toString();
	}

}
