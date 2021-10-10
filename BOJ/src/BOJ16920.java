import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16920 {
	static int n;
	static int m;
	static int p;
	static char map[][];
	static int score[];
	static int expansion[];
	static PriorityQueue<coordinate> player = new PriorityQueue<coordinate>();
	static int dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}}; 
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		score = new int[p+1];
		expansion = new int[p+1];
		st = new StringTokenizer(br.readLine());
		visited = new boolean[n][m];
		
		for(int i = 1; i < p+1; i++) {
			expansion[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < m; j++) {
				if(map[i][j] != '#' && map[i][j] != '.') {
					player.add(new coordinate(Character.getNumericValue(map[i][j]), i, j, 0));
				}				
			}
		}
		
		boolean check = true;
		while(check) check = game();
		 
		countScore();
		for(int i = 1; i < p+1; i++) {
			System.out.print(score[i] + " ");
		}
	}
	
	static boolean game() {
		boolean check = false;
		PriorityQueue<coordinate> temp = new PriorityQueue<coordinate>();
		
		while(!player.isEmpty()) {
			coordinate cur = player.poll();

			if(visited[cur.x][cur.y]) continue;
			visited[cur.x][cur.y] = true;
			map[cur.x][cur.y] = (char)(cur.idx+'0');
			
			if(cur.count == expansion[cur.idx]) {
				visited[cur.x][cur.y] = false;
				temp.add(new coordinate(cur.idx, cur.x, cur.y, 0));
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dxy[i][0];
				int ty = cur.y + dxy[i][1];
				if(!IsRange(tx, ty)) continue;
				if(map[tx][ty] != '.') continue;
				player.add(new coordinate(cur.idx, tx, ty, cur.count+1));
			}
		}
		
		check = IsConitnue();
		player = temp;
		return check;
	}
	
	static boolean IsRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

	static void countScore() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] != '#' && map[i][j] != '.') {
					score[Character.getNumericValue(map[i][j])]++;
				}
			}
		}
	}
	
	static boolean IsConitnue() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == '.') {
					return true;
				}
			}
		}
		return false;
	}
	
	static class coordinate implements Comparable<coordinate>{
		int idx;
		int x;
		int y;
		int count;
		
		coordinate(int idx, int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.idx = idx;
			this.count = count;
		}
		
		@Override
		public int compareTo(coordinate o) {
			if(idx == o.idx) {
				return count - o.count;
			}else {
				return idx - o.idx;
			}
			
		}
	}

}
