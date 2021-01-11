import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707 {
	static int colors[];
	static ArrayList<ArrayList<Integer>> al;
	static boolean check;
	static String result = "";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			al = new ArrayList<>();
			colors = new int[v];
			check = true;
			
			for(int j = 0; j < v; j++) {
				al.add(new ArrayList<Integer>());
			}
			
			for(int j = 0; j < e; j++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken())-1;
				int v2 = Integer.parseInt(st.nextToken())-1;
				
				al.get(v1).add(v2);
				al.get(v2).add(v1);
			}
			
			for(int j = 0; j < v; j++) {
				if(colors[j] == 0) {
					//bfs(j, 1);
					dfs(j, 1);
				}
				if(!check) {
					break;
				}
			}
			
			if(check) {
				result += "YES"+"\n";
			}else {
				result += "NO"+"\n";
			}
		}

		System.out.println(result);
		
	}
	

	static void dfs(int now, int color) {
		colors[now] = color;
		
		for(Integer next: al.get(now)) {
			if(colors[next] == color) {
				check = false;
				return;
			}
			
			if(colors[next] == 0) {
				dfs(next, color*-1);
			}
		}
	}
/*
	static void bfs(int now, int color) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(now);
		colors[now] = color;
		
		while(!q.isEmpty() && check) {
			int cur = q.poll();
			
			for(int next: al.get(cur)) {
				
				if(colors[next] == 0) {
					q.offer(next);
					colors[next] = colors[cur]*-1;
				}
				
				if(colors[next] + colors[cur] != 0) {
					check = false;
					return;
				}
				
			}
		}
	}*/
}
