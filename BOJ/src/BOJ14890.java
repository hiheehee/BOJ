import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ14890 {

	static int map[][];
	static int result;
	static int N, L;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		result = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		slide();
		System.out.print(result);
	}
	
	static void slide() {
		for(int i = 0; i < N; i++) {
			if(isAllSame(0, i, N, 0)) {
				result++;
				continue;
			}else {
				if(isRunway(0, i, 0)) {
					result++;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			if(isAllSame(i, 0, N, 1)) {
				result++;
				continue;
			}else {
				if(isRunway(i, 0, 1)) {
					result++;
				}
			}
		}
	}
	
	static boolean isRunway(int x, int y, int d) {
		boolean visited[] = new boolean[N];
		if(d == 0) { // 행방향
			for(int i = 0; i < N-1; i++) {
				if(map[i][y] == map[i+1][y]) continue;
				if(Math.abs(map[i][y] - map[i+1][y]) >= 2) return false;
				
				if(map[i][y] + 1 == map[i+1][y]) {
					if(i - L + 1 < 0) return false;
					if(!isAllSame(i - L + 1, y, L, 0)) return false;
					for(int j = i - L + 1; j < i+1; j++) {
						if(visited[j]) return false;
						visited[j] = true;
					}
				}else if(map[i][y] == 1 + map[i+1][y]){
					if(N <= i + L) return false;
					if(!isAllSame(i+1, y, L, 0)) return false;
					for(int j = i+1; j < i+L+1; j++) visited[j] = true;
				}
			}
		}else { // 열방향
			for(int i = 0; i < N-1; i++) {
				if(map[x][i] == map[x][i+1]) continue;
				if(Math.abs(map[x][i] - map[x][i+1]) >= 2) return false;
				
				if(map[x][i] + 1 == map[x][i+1]) { // 올라갈때
					if(i - L + 1 < 0) return false;
					if(!isAllSame(x, i - L + 1, L, 1)) return false;
					for(int j = i - L + 1; j < i+1; j++) {
						if(visited[j]) return false;
						visited[j] = true;
					}
				}else if(map[x][i] == 1 + map[x][i+1]){ // 내려갈때
					if(N <= i + L) return false;
					if(!isAllSame(x, i + 1, L, 1)) return false;
					for(int j = i+1; j < i+L+1; j++) visited[j] = true;
				}
			}
		}
		return true;
	}
	
	static boolean isAllSame(int x, int y, int len, int d) {
		if(d == 0) { // 행방향
			for(int i = x; i < len+x; i++) {
				if(map[x][y] != map[i][y]) return false; 
			}
		}else { // 열방향
			for(int i = y; i < len+y; i++) {
				if(map[x][y] != map[x][i]) return false; 
			}
		}
		return true;
	}

}
