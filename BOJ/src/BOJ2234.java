import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2234 {
	static int n;
	static int m;
	static int map[][];
	static int roomCount = 0;
	static int maxRoom = 0;
	static int wallRoom = 0;
	static HashMap<Integer, String> hm = new HashMap<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		hm.put(1, "0 -1"); // 서쪽
		hm.put(2, "-1 0"); // 북쪽
		hm.put(4, "0 1"); // 동쪽
		hm.put(8, "1 0"); // 남쪽
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	}
	
	static void bfs(int x, int y) {
		Queue<coordiante> q = new LinkedList<>();
		boolean visited[][][] = new boolean[m][n][2];
		q.offer(new coordiante(x, y, 0, 0));
		int tempRoom = 0;
		int tempWallRoom = 0;
		
		while(!q.isEmpty()) {
			coordiante cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				String temp[] = hm.get((int)Math.pow(2, i)).split(" ");
				int tx = cur.x + Integer.parseInt(temp[0]);
				int ty = cur.y + Integer.parseInt(temp[1]);
				
				if((map[cur.x][cur.y] & (1<<i)) == 0) { // 벽이 없는 경우				
					if(tx < 0 || m <= tx || ty < 0 || n <= ty) {
						continue;
					}
					
					if(visited[tx][ty][cur.count]) {
						continue;
					}
					tempRoom++;
					tempWallRoom++;
					q.offer(new coordiante(tx, ty, cur.count+1, cur.wall));
				}else if((map[cur.x][cur.y] & (1<<i)) == 1 && cur.wall == 0) {
					if(tx < 0 || m <= tx || ty < 0 || n <= ty) {
						continue;
					}
					
					if(visited[tx][ty][cur.wall+1]) {
						continue;
					}
					tempWallRoom++;
					q.offer(new coordiante(tx, ty, cur.count+1, cur.wall+1));
				}
			}
		}
		
		maxRoom = Math.max(maxRoom, tempRoom);
		wallRoom = Math.max(wallRoom, tempWallRoom);
	}
	
	static class coordiante {
		int x;
		int y;
		int count;
		int wall;
		
		coordiante(int x, int y, int count, int wall) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.wall = wall;
		}
	}

}
