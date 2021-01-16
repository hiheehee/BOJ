import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {
	static Queue<coordinate> water = new LinkedList<>(); // 물 위치
	static Queue<coordinate> hedgehog = new LinkedList<>(); // 고슴도치 위치
	static int r;
	static int s;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static char map[][];
	static boolean visited[][];
	static String result = "";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		map = new char[r][s];
		visited = new boolean[r][s];
		
		for(int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < s; j++) {
				if(map[i][j] == '*') {
					water.offer(new coordinate(i, j, 0));
				}else if(map[i][j] == 'S') {
					hedgehog.offer(new coordinate(i, j, 0));
				}
			}
		}
		
		bfs();
		
		if(result.equals("")) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
		
		
		
	}

	static void bfs() {
		
		while(!water.isEmpty() || !hedgehog.isEmpty()) {
			
			if(!water.isEmpty()) {	
				coordinate cur = water.poll();
				if(!result.equals("")) {
					return;
				}
			
				while(!hedgehog.isEmpty() && hedgehog.peek().second < cur.second) {
					coordinate animal = hedgehog.poll();
				
					for(int i = 0; i < 4; i++) {
						int x = animal.x + dx[i];
						int y = animal.y + dy[i];
					
						// map 범위 초과
						if(x < 0 || r <= x || y < 0 || s <= y) {
							continue;
						}
					
						if(map[x][y] == 'X' || map[x][y] == '*'){
							continue;
						}
					
						if(map[x][y] == '.' && !visited[x][y]) {
							map[x][y] = 'S';
							visited[x][y] = true;
							map[animal.x][animal.y] = '.';
							hedgehog.offer(new coordinate(x, y, animal.second+1));
						
						}
					
						if(map[x][y] == 'D') { // 고슴도치가 비버굴에 도착한 경우
							result = Integer.toString(animal.second+1);
							return;
						}
					}
				}
			
			
				for(int i = 0; i < 4; i++) {
					int x = cur.x + dx[i];
					int y = cur.y + dy[i];
				
					// map 범위 초과
					if(x < 0 || r <= x || y < 0 || s <= y) {
						continue;
					}
				
					if(map[x][y] == 'X' || map[x][y] == 'D' || map[x][y] == '*'){
						continue;
					}
					
					map[x][y] = '*';
					water.offer(new coordinate(x, y, cur.second+1));
				}	
			}else {
				while(!hedgehog.isEmpty()) {
					coordinate animal = hedgehog.poll();
					for(int i = 0; i < 4; i++) {
						int x = animal.x + dx[i];
						int y = animal.y + dy[i];
					
						// map 범위 초과
						if(x < 0 || r <= x || y < 0 || s <= y) {
							continue;
						}
					
						if(map[x][y] == 'X' || map[x][y] == '*'){
							continue;
						}
					
						if(map[x][y] == '.' && !visited[x][y]) {
							map[x][y] = 'S';
							visited[x][y] = true;
							map[animal.x][animal.y] = '.';
							hedgehog.offer(new coordinate(x, y, animal.second+1));
						
						}
					
						if(map[x][y] == 'D') { // 고슴도치가 비버굴에 도착한 경우
							result = Integer.toString(animal.second+1);
							return;
						}
					}
				}
			}
			
			
					
			
		}
	}

}

class coordinate {
	int x;
	int y;
	int second;
	
	coordinate(int x, int y, int second) {
		this.x = x;
		this.y = y;
		this.second = second;
	}
}
