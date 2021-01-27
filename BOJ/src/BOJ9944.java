import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9944 {
	static char map[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,1,-1};
	static int result;
	static int n;
	static int m;
	static int blank = 0;
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		int count = 1;
		while((str = br.readLine()) != null) {
			String num[] = str.split(" ");
			n = Integer.parseInt(num[0]); // За
			m = Integer.parseInt(num[1]); // ї­
			map = new char[n][m];
			result = Integer.MAX_VALUE;
			visited = new boolean[n][m];
			blank = 0;
			
			for(int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j < m; j++) {
					if(map[i][j] == '.') {
						blank++;
					}
				}
			}
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(map[i][j] == '.') {
						visited[i][j] = true;
						dfs(0, i, j, 1);
						visited[i][j] = false;
					}
				}
			}
			
			if(result == Integer.MAX_VALUE) result = -1;
			System.out.println("Case "+count+": "+result);
		}
		

	}
	
	static void dfs(int len, int x, int y, int count) {
		if(count == blank) {
			result = Math.min(result, count);
		}

		for(int i = 0; i < 4; i++) {
			int r = x;
			int c = y;
			int cnt = 0;
			int nr, nc;
			
			while(true) {
				nr = r + dx[i];
				nc = c + dy[i];
				
				if(r < 0 || n <= r || c < 0 || m <= c) {
					break;
				}
				
				if(map[r][c] == '*' || visited[r][c]) {
					break;
				}
				
				visited[r][c] = true;
				cnt++;
				r = nr;
				c = nc;
			}
			
			if(r == x && c == y) continue;
			
			dfs(len + 1, r, c, count+cnt);
			for(int j = 0; j < cnt; j++) {
				visited[r][c] = false;
				r -= dx[j];
				c -= dy[j];
			}
		}
		
	}
}
