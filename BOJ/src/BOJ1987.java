import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1987 {
	static int r, c;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static char board[][];
	static int result = 0;
	static HashSet<Character> hs = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r][c];
		
		for(int i = 0; i < r; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		hs.add(board[0][0]);
		dfs(0, 0, 1);
		System.out.print(result);
		
	}
	
	static void dfs(int x, int y, int count) {
		for(int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx < 0 || r <= tx || ty < 0 || c <= ty) {
				continue;
			}
			
			if(hs.contains(board[tx][ty])) {
				result = Math.max(result, count);
			}else {
				hs.add(board[tx][ty]);
				dfs(tx, ty, count+1);
				hs.remove(board[tx][ty]);
			}
			
		}
	}

	static class point{
		int x;
		int y;
		
		point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
