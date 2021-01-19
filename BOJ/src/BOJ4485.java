import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4485 {
	static int map[][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int ind = 1;
		
		while(n != 0) {
			map = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dijsktra(ind);
			ind++;
			n = Integer.parseInt(br.readLine());
		}
		
	}
	
	static void dijsktra(int index) {
		PriorityQueue<coordinate> pq = new PriorityQueue<>();
		boolean visited[][] = new boolean[n][n];
		pq.offer(new coordinate(0, 0, map[0][0]));
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			coordinate cur = pq.poll();
			
			if(cur.x == n-1 && cur.y == n-1) {
				System.out.println("Problem "+index+": "+cur.rupee);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int x = cur.x + dx[i];
				int y = cur.y + dy[i];
				
				if(x < 0 || n <= x || y < 0 || n <= y) {
					continue;
				}
				
				if(visited[x][y]) {
					continue;
				}
				
				visited[x][y] = true;
				pq.offer(new coordinate(x, y, cur.rupee + map[x][y]));
			}
		}
		
	}

	static class coordinate implements Comparable<coordinate> {
		int x;
		int y;
		int rupee;
		
		coordinate(int x, int y, int rupee) {
			this.x = x;
			this.y = y;
			this.rupee = rupee;
		}

		@Override
		public int compareTo(coordinate o) {
			return rupee - o.rupee;
		}
		
		
	}
}
