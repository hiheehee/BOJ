import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261 {
	// (x+1, y), (x, y+1), (x-1, y), (x, y-1) 이동
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	static int m;
	static int n;
	static int maze[][];
	static int visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 가로
		n = Integer.parseInt(st.nextToken()); // 세로
		
		maze = new int[n][m];
		visited = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			String str[] = br.readLine().split("");
			Arrays.fill(visited[i], Integer.MAX_VALUE);
			for(int j = 0; j < m; j++) {
				maze[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		breakingTheWall();
		System.out.println(visited[n-1][m-1]);

	}
	
	static void breakingTheWall() {
		PriorityQueue<wall> q = new PriorityQueue<>();
		q.offer(new wall(0, 0, 0));
		visited[0][0] = 0;
		
		while(!q.isEmpty()) {
			wall cur = q.poll();

			if(cur.x == n-1 && cur.y == m) {
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int x = cur.x + dx[i];
				int y = cur.y + dy[i];
				
				if(x < 0 || n <= x || y < 0 || m <= y) {
					continue;
				}
				
				if(visited[x][y] != Integer.MAX_VALUE) {
					continue;
				}
				
				if(maze[x][y] == 0) {
					q.offer(new wall(x, y, cur.count));
					visited[x][y] = cur.count;
				}else {
					q.offer(new wall(x, y, cur.count + 1));
					visited[x][y] = cur.count + 1;
				}
				
			}
		}
		
		
	}

}

class wall implements Comparable<wall>{
	int x;
	int y;
	int count;
	
	wall(int x, int y, int count){
		this.x = x;
		this.y = y;
		this.count = count;
	}

	@Override
	public int compareTo(wall o) {
		return this.count - o.count;
	}
}
