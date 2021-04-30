import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {
	static int n, m;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static int result = 0;
	static ArrayList<point> zero = new ArrayList<>();
	static ArrayList<point> two = new ArrayList<>();
	static int map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) zero.add(new point(i, j));
				if (map[i][j] == 2) two.add(new point(i, j));
			}
		}
		
		for(int i = 0; i < zero.size()-2; i++) {
			for(int j = 1; j < zero.size()-1; j++) {
				for(int k = 2; k < zero.size(); k++) {
					if(i != j && j != k && i != k) {
						int temp[][] = copy();
						temp[zero.get(i).x][zero.get(i).y] = 1;
						temp[zero.get(j).x][zero.get(j).y] = 1;
						temp[zero.get(k).x][zero.get(k).y] = 1;
						bfs(temp);
					}
					
				}
			}
		}

		System.out.println(result);
	}

	static int[][] copy(){
		int temp[][] = new int[n][m];
		
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < m; j++) 
				temp[i][j] = map[i][j];
		
		return temp;	
	}
	
	static void bfs(int arr[][]) {
		Queue<point> q = new LinkedList<>();

		for(point i: two)
			q.offer(i);

		while(!q.isEmpty()) {
			point cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				
				if(tx < 0 || n <= tx || ty < 0 || m <= ty) {
					continue;
				}
				
				if(arr[tx][ty] == 0) {
					arr[tx][ty] = 2;
					q.offer(new point(tx, ty));
				}
			}
		}
		
		safeZone(arr);
	}
	
	static void safeZone(int arr[][]) {
		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 0) {
					count++;
				}
			}
		}
		result = Math.max(result, count);
	}

	static class point {
		int x;
		int y;
		
		point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
