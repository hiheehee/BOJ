import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {
	static int map[][];
	static int dx[] = {-1,-2,-2,-1,1,2,2,1};
	static int dy[] = {-2,-1,1,2,-2,-1,1,2};
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			System.out.println(bfs(sx,sy,ex,ey));
		}

	}
	
	static int bfs(int sx, int sy, int ex, int ey) {
		Queue<point> q = new LinkedList<>();
		q.offer(new point(sx, sy, 0));
		int visited[][] = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		visited[sx][sy] = 0;
		while(!q.isEmpty()) {
			point temp = q.poll();
			
			if(temp.x == ex && temp.y == ey) {
				return temp.count;
			}
			
			for(int i = 0; i < 8; i++) {
				int tx = temp.x + dx[i];
				int ty = temp.y + dy[i];
				
				if(tx < 0 || n <= tx || ty < 0 || n <= ty) {
					continue;
				}
				
				if(temp.count+1 < visited[tx][ty]) {
					q.offer(new point(tx, ty, temp.count+1));
					visited[tx][ty] = temp.count+1;
				}
				
			}
		}
		return -1;
	}
	
	static class point{
		int x;
		int y;
		int count;
		
		point(int x, int y, int count){
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}

}
