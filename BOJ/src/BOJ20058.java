import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20058 {
	static int N, Q;
	static int map[][];
	static boolean visited[][];
	static int dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map = new int[(int)Math.pow(2, N)][(int)Math.pow(2, N)];
		
		for(int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			int ind = Integer.parseInt(st.nextToken());
			split(0, 0, (int)Math.pow(2, N), (int)Math.pow(2, ind));
			MeltIce();
			//print();
		}
		
		visited = new boolean[(int)Math.pow(2, N)][(int)Math.pow(2, N)];
		iceSum();
		searchMaxIce();
	}
	
	static boolean isRange(int x, int y) {
		return -1 < x && x < map.length && -1 < y && y < map.length; 
	}
	/*
	static void print() {
		System.out.println();
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]+" ");
			}System.out.println();
		}System.out.println();
	}
	*/
	static void MeltIce() {
		visited = new boolean[(int)Math.pow(2, N)][(int)Math.pow(2, N)];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				int count = 0;
				for(int k = 0; k < 4; k++) {
					int tx = i + dxy[k][0];
					int ty = j + dxy[k][1];
					if(isRange(tx, ty) && map[tx][ty] == 0) {
						count++;
					}else if(!isRange(tx, ty)){
						count++;
					}
				}
				if(count >= 2) visited[i][j] = true;
			}
		}
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(visited[i][j] && map[i][j] > 0) map[i][j]--;
			}
		}
	}
	
	static void cycle(int x, int y, int s) {
		if (s <= 1) {
			return;
		}
		
		int temp[] = new int[s];
		for(int i = y; i < y + s; i++) {
			temp[i-y] = map[x][i];
		}
		
		int ind = y+s;
		for(int i = x; i < x + s; i++) {
			ind--;
			map[x][ind] = map[i][y];
		}

		ind = x-1;
		for(int i = y; i < y + s; i++) {
			ind++;
			map[ind][y] = map[x+s-1][i];
		}
		
		ind = y-1;
		for(int i = x+s-1; x-1 < i; i--) {
			ind++;
			map[x+s-1][ind] = map[i][y+s-1];
		}
		
		ind = x+s;
		for(int i = temp.length-1; -1 < i; i--) {
			ind--;
			map[ind][y+s-1] = temp[i];
		}
		
		cycle(x+1, y+1, s-2);
	}
	
	static void split(int x, int y, int s, int goal) {
		if(s == goal) {
			cycle(x, y, s);
			return;
		}
		split(x , y, s/2,  goal);
		split(x + s/2, y, s/2,  goal);
		split(x , y + s/2, s/2,  goal);
		split(x + s/2, y + s/2, s/2,  goal);
	}
	
	static void searchMaxIce() {
		int max = 0;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(map[i][j] > 0 && !visited[i][j]) {
					max = Math.max(max, bfs(i, j));
				}
			}
		}
		System.out.print(max);
	}
	
	static int bfs(int x, int y) {
		Queue<coordinate> q = new LinkedList<>();
		q.add(new coordinate(x, y));
		int count = 0;
		
		while(!q.isEmpty()) {
			coordinate cur = q.poll();
			
			if (visited[cur.x][cur.y]) continue;
			count++;
			visited[cur.x][cur.y] = true;
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dxy[i][0];
				int ty = cur.y + dxy[i][1];
				if(!isRange(tx, ty)) continue;
				if(map[tx][ty] == 0) continue;
				q.add(new coordinate(tx, ty));
			}
		}
		
		return count;
	}
	
	static void iceSum() {
		int sum = 0;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				sum += map[i][j];
			}
		}
		
		System.out.println(sum);
	}
	
	static class coordinate {
		int x;
		int y;
		
		coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
