import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17086 {

	static int safeZone[][];
	static int n;
	static int m;
	static ArrayList<String> shark = new ArrayList<>();
	static int dxy[][] = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		safeZone = new int[n][m];
		int result = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					shark.add(i+" "+j);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				safeZone[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(String s:shark) {
			move(s);
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				result = Math.max(result, safeZone[i][j]);
			}
		}
		
		System.out.println(result);
	}
	
	static void move(String baby) {
		String str[] = baby.split(" ");
		int x = Integer.parseInt(str[0]);
		int y = Integer.parseInt(str[1]);
		safeZone[x][y] = 0;
		Queue<String> q = new LinkedList<>();
		q.add(baby);
		boolean visited[][] = new boolean[n][m];
		
		while(!q.isEmpty()) {
			String cur[] = q.poll().split(" ");
			int cx = Integer.parseInt(cur[0]);
			int cy = Integer.parseInt(cur[1]);
			visited[cx][cy] = true;
			for(int i = 0; i < 8; i++) {
				int tx = cx + dxy[i][0];
				int ty = cy + dxy[i][1];
				
				if(!isRange(tx, ty)) continue;
				if(visited[tx][ty]) continue;
				if(safeZone[cx][cy] + 1 <= safeZone[tx][ty]) {
					safeZone[tx][ty] = safeZone[cx][cy] + 1;
					q.add(tx+" "+ty);
				}
			}
		}
	}
	
	static boolean isRange(int x, int y) {
		return -1 < x && x < n && -1 < y && y < m; 
	}

}
