import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144 {
	static int[][][] dust;
	static int r, c;
	static int air[] = new int[2];
	static int dxy[][] = {{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		dust = new int[r][c][2];
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				dust[i][j][0] = Integer.parseInt(st.nextToken());
				if(dust[i][j][0] == -1) {
					if(0 < air[0]) {
						air[1] = i;
					}else {
						air[0] = i;
					}
				}
			}
		}

		while(t-- > 0) {
			spead();
			cycle();
		}
		
		System.out.println(dustCount());
	}
	
	static int dustCount() {
		int result = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				result += dust[i][j][0];
			}
		}
		return result+2;
	}

	static boolean isRange(int x, int y) {
		return -1 < x && x < r && -1 < y && y < c;
	}
	
	static void cycle() {
		// banClock
		for(int i = air[0]-2; -1 < i; i--) {
			dust[i+1][0][0] = dust[i][0][0];
		}
		
		for(int i = 1; i < c; i++) {
			dust[0][i-1][0] = dust[0][i][0];
		}
		
		for(int i = 1; i <= air[0]; i++) {
			dust[i-1][c-1][0] = dust[i][c-1][0];
		}
		
		for(int i = c-1; 1 < i; i--) {
			dust[air[0]][i][0] = dust[air[0]][i-1][0];
		}
		dust[air[0]][1][0] = 0;
		// Clock
		for(int i = air[1]+2; i < r; i++) {
			dust[i-1][0][0] = dust[i][0][0];
		}
		
		for(int i = 1; i < c; i++) {
			dust[r-1][i-1][0] = dust[r-1][i][0];
		}
		
		for(int i = r-2; air[1] <= i; i--) {
			dust[i+1][c-1][0] = dust[i][c-1][0];
		}
		
		for(int i = c-1; 1 < i; i--) {
			dust[air[1]][i][0] = dust[air[1]][i-1][0];
		}
		dust[air[1]][1][0] = 0;
	}

	static void spead() {
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(dust[i][j][0] > 0) {
					int count = 0;
					for(int k = 0; k < 4; k++) {
						int tx = i + dxy[k][0];
						int ty = j + dxy[k][1];
						if(!isRange(tx, ty)) continue;
						if(dust[tx][ty][0] == -1) continue;
						count++;
						dust[tx][ty][1] += dust[i][j][0]/5;
					}
					dust[i][j][0] -= dust[i][j][0]/5*count;
				}
			}
		}
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(dust[i][j][1] > 0) {
					dust[i][j][0] += dust[i][j][1];
					dust[i][j][1] = 0;
				}
			}
		}
	}
}
