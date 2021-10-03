import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21610 {

	static int n, m;
	static int map[][];
	static int cloudMove[][];
	static boolean clouds[][];
	static int dxy[][] = {{0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}}; // ¡ç, ¢Ø, ¡è, ¢Ö, ¡æ, ¢Ù, ¡é, ¢× 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		clouds = new boolean[n][n];
		cloudMove = new int[m][2];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			cloudMove[i][0] = d;
			cloudMove[i][1] = s;
		}
		
		init();
		for(int i = 0; i < m; i++) {
			rain(cloudMove[i][0], cloudMove[i][1]);
			copybug(cloudMove[i][0], cloudMove[i][1]);
			makeClouds();
		}

		System.out.print(water());
	}
	
	static void init() {
		clouds[n-1][0] = true;
		clouds[n-1][1] = true;
		clouds[n-2][0] = true;
		clouds[n-2][1] = true;
	}
	
	static int water() {
		int sum = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	static void makeClouds() {
		boolean tc[][] = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] >= 2 && !clouds[i][j]) {
					tc[i][j] = true;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(tc[i][j]) {
					map[i][j] -= 2;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.arraycopy(tc[i], 0, clouds[i], 0, n);
		}
	}
	
	static void copybug(int d, int s) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(clouds[i][j]) {
					int count = 0;
					for(int k = 1; k < 8; k += 2) {
						int tx = i + dxy[k][0];
						int ty = j + dxy[k][1];
						if(isRange(tx, ty) && map[tx][ty] > 0) count++;
					}
					map[i][j] += count;
				}
			}
		}
	}

	static void rain(int d, int s) {
		boolean tc[][] = new boolean[n][n];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(clouds[i][j]) {
					int tx = i + dxy[d][0]*s%n;
					int ty = j + dxy[d][1]*s%n;
					
					if(!isRange(tx, ty)) {
						if(tx < 0) {
							tx += n;
						}else if(n <= tx) {
							tx -= n;
						}
						
						if(ty < 0) {
							ty += n;
						}else if(n <= ty) {
							ty -= n;
						}
					}

					tc[tx][ty] =  true;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(tc[i][j]) {
					map[i][j]++;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.arraycopy(tc[i], 0, clouds[i], 0, n);
		}
	}
	
	static boolean isRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

}
