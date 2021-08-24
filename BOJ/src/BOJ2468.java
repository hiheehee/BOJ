import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468 {

	static int safeZone = 0;
	static int maxWater = 0;
	static int minWater = 101;
	static int map[][];
	static int dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int n;
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxWater = Math.max(maxWater, map[i][j]);
				minWater = Math.min(minWater, map[i][j]);
			}
		}
		if(minWater == maxWater) {
			safeZone = 1;
		}else {
			for(int k = minWater; k < maxWater; k++) {
				visited = new boolean[n][n];
				int count = 0;
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(!visited[i][j] && k < map[i][j]) {
							count++;
							bfs(i, j, k);
						}
					}
				}
				safeZone = Math.max(safeZone, count);
			}
		}
		System.out.println(safeZone);
	}

	static void bfs(int x, int y, int water) {
		Queue<String> q = new LinkedList<>();
		q.add(x+" "+y);
		visited[x][y] = true;
		while(!q.isEmpty()) {
			String cur[] = q.poll().split(" ");
			int cx = Integer.parseInt(cur[0]);
			int cy = Integer.parseInt(cur[1]);
			for(int i = 0; i < 4; i++) {
				int tx = cx + dxy[i][0];
				int ty = cy + dxy[i][1];
				
				if(!isRange(tx, ty)) continue;
				if(visited[tx][ty]) continue;
				if(water < map[tx][ty]) {
					q.add(tx+" "+ty);
					visited[tx][ty] = true;
				}
			}
		}
	}

	static boolean isRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n; 
	}
}
