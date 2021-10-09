import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ18405 {

	static int map[][];
	static int dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int x, y, s, n;
	static boolean visited[][];
	static PriorityQueue<coordinate> q = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) q.add(new coordinate(map[i][j], i, j));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken())-1;
		y = Integer.parseInt(st.nextToken())-1;
		
		while(s-- >= 0) {
			bfs();
			if(map[x][y] != 0) break;
		}
		
		System.out.println(map[x][y]);
	}
	
	static class coordinate implements Comparable<coordinate>{
		int idx;
		int x;
		int y;
		
		coordinate(int idx, int x, int y){
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(coordinate o) {
			return this.idx - o.idx;
		}
	}
	
	static void bfs() {
		PriorityQueue<coordinate> tq = new PriorityQueue<>();
		
		while(!q.isEmpty()) {
			coordinate cur = q.poll();
			if(visited[cur.x][cur.y]) continue;
			visited[cur.x][cur.y] = true;
			map[cur.x][cur.y] = cur.idx;
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dxy[i][0];
				int ty = cur.y + dxy[i][1];
				if(!isRange(tx, ty)) continue;
				if(visited[tx][ty]) continue;
				
				tq.add(new coordinate(cur.idx, tx, ty));
			}
		}
		
		q = tq;
		
	}
	
	static boolean isRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

}
