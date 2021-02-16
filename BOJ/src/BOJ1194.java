import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1194 {
	static char map[][];
	static boolean visited[][][];
	static int n;
	static int m;
	static int result = 987654321;
	static Queue<coordinate> q = new LinkedList<>();
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new boolean[n][m][(1<<7)-1];
		
		for(int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < m; j++) {
				if(map[i][j] == '0') {
					q.offer(new coordinate(i, j, 0, 0));
					map[i][j] = '.';
				}
			}
		}

		bfs();
		if(result == 987654321) System.out.println(-1);
		else System.out.println(result);
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			coordinate cur = q.poll();
			
			if(visited[cur.x][cur.y][cur.key]) continue;
			visited[cur.x][cur.y][cur.key] = true;
			
			for(int i = 0; i < 4; i++) {
				int x = dxy[i][0] + cur.x;
				int y = dxy[i][1] + cur.y;
				
				if(x < 0 || n <= x || y < 0 || m <= y)	continue;
				if(map[x][y] == '1') {
					result = Math.min(result, cur.count+1);
				}
				
				if(map[x][y] == '.') {
					q.offer(new coordinate(x, y, cur.key, cur.count+1));
				}else if('a' <= map[x][y] && map[x][y] <= 'z') {
					q.offer(new coordinate(x, y, cur.key | (1<<((int)map[x][y]-97)), cur.count+1));
				}else if('A' <= map[x][y] && map[x][y] <= 'Z') {
					int key = cur.key & (1<<((int)map[x][y]-65));
					if(key > 0) {
						q.offer(new coordinate(x, y, cur.key, cur.count+1));
					}
				}
				
			}
		}
	}
	
	static class coordinate {
		int x;
		int y;
		int key;
		int count;
		
		coordinate(int x, int y, int key, int count){
			this.x = x;
			this.y = y;
			this.key = key;
			this.count = count;
		}
	}

}
