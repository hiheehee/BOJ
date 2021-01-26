import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2210 {
	static String map[][] = new String[5][5];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static HashSet<String> hs = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			map[i] = br.readLine().split(" ");
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				bfs(new coordinate(i, j, map[i][j]));
			}
		}
		
		System.out.println(hs.size());
	}
	
	static void bfs(coordinate start) {
		Queue<coordinate> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			coordinate cur = q.poll();
			
			if(cur.str.length() == 6) {
				hs.add(cur.str);
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int x = cur.x + dx[i];
				int y = cur.y + dy[i];
				
				if(x < 0 || 5 <= x || y < 0 || 5 <= y) {
					continue;
				}
				
				q.offer(new coordinate(x, y, cur.str+map[x][y]));
			}
		}
	}
	
	static class coordinate {
		int x;
		int y;
		String str;
		
		coordinate(int x, int y, String str) {
			this.x = x;
			this.y = y;
			this.str = str;
		}
	}

}
