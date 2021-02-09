import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BOJ11725 {
	static int parents[];
	static ArrayList<Integer>[] al;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		parents = new int[n+1];
		al = new ArrayList[n+1];
		
		
		for(int i = 0; i < n+1; i++) {
			al[i] = new ArrayList<>();
		}

		for(int i = 0; i < n-1; i++) {
			String str[] = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			al[a].add(b);
			al[b].add(a);
		}
		SearchParents();
		for(int i = 2; i < n+1; i++) {
			System.out.println(parents[i]);
		}
	}
	
	static void SearchParents() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		parents[1] = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next: al[cur]) {
				if(parents[next] == 0) {
					q.offer(next);
					parents[next] = cur;
				}
			}
		}
		
	}
}
