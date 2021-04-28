import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9372 {
	static ArrayList<Integer>[] al;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			al = new ArrayList[n+1];
			visited = new boolean[n+1];
			
			for(int j = 0; j < n+1; j++) {
				al[j] = new ArrayList<Integer>();
			}
			
			for(int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				al[a].add(b);
				al[b].add(a);
			}

			System.out.println(CountAirplane());
		}
	}
	
	static int CountAirplane() {
		Queue<Integer> q = new LinkedList<>();
		int count = 0;
		q.add(1);
		visited[1] = true;
		while(!q.isEmpty()) {
			int node = q.poll();
			for(Integer i: al[node]) {
				if(!visited[i]) {
					q.add(i);
					visited[i] = true;
					count++;
				}
			}
		}
		
		return count;
	}

}
