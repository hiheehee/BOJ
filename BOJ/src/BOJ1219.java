import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1219 {
	static int n; // 도시의 수 N
	static int s; // 시작 도시
	static int e; // 도착 도시
	static int m; // 교통 수단의 개수 M
	static long dis[];
	static ArrayList<edge>[] road;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int city[] = new int[n];
		
		road = new ArrayList[n];
		dis = new long[n];
		for(int i = 0; i < n; i++) {
			road[i] = new ArrayList<>();
		}
		Arrays.fill(dis, -987654321);
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			road[a].add(new edge(b, -c));
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			city[i] = Integer.parseInt(st.nextToken());
			if(i == s) {
				dis[s] = city[i];
			}
		}

		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < road[i].size(); j++) {
				road[i].get(j).time += city[road[i].get(j).node];
			}
		}
		
		bellmanFord();

	}
	
	static boolean bellmanFord() {

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < road.length; j++) {
				for(edge next: road[j]) {
					if(dis[j] != -987654321 && dis[j] + next.time > dis[next.node]) {
						dis[next.node] = dis[j] + next.time;
					}
				}
			}
		}
		
		if(dis[e] == -987654321) {
			System.out.println("gg");
			return true;
		}else {
			for(int j = 0; j < road.length; j++) {
				for(edge next: road[j]) {
					if(dis[next.node] != -987654321 && dis[j] + next.time > dis[next.node]) {
						System.out.println("Gee");
						return true;
					}
				}
			}
			System.out.println(dis[e]);
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
