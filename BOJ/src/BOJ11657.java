import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11657 {
	static int n;
	static int m;
	static long dis[];
	static ArrayList<edge>[] road;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		road = new ArrayList[n+1];
		dis = new long[n+1];
		Arrays.fill(dis, 5000001);
		
		for(int i = 0; i < n+1; i++) {
			road[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			road[a].add(new edge(b,c));
		}

		if(bellmanFord()) {
			System.out.println(-1);
		}else {
			for(int i = 2; i < n+1; i++) {
				if(dis[i] == 5000001) {
					System.out.println(-1);
				}else {
					System.out.println(dis[i]);
				}
				
			}
		}
	}
	
	static boolean bellmanFord() {
		dis[1] = 0;

		for(int i = 1; i < n; i++) {
			for(int j = 1; j < road.length; j++) {
				for(edge next: road[j]) {
					if(dis[j] != 5000001 && dis[j] + next.time < dis[next.node]) {
						dis[next.node] = dis[j] + next.time;
					}
				}
			}
		}
		
		for(int j = 1; j < road.length; j++) {
			for(edge next: road[j]) {
				if(dis[j] != 5000001 && dis[j] + next.time < dis[next.node]) {
					return true;
				}
			}
		}
		return false;

	}

	static class edge {
		int node;
		int time;
		
		edge(int node, int time) {
			this.node = node;
			this.time = time;
		}
	}
}
