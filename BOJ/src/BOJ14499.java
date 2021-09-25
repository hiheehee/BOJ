import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499 {
	static int dice[] = {0,0,0,0,0,0}; // U, F, D, B, L, R
	static int N, M;
	static int map[][];
	static int dxy[][] = {{0,1},{0,-1},{-1,0},{1,0}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			int ind = Integer.parseInt(st.nextToken())-1;
			if(game(x, y, ind)) {
				System.out.println(dice[0]);
				x = x + dxy[ind][0];
				y = y + dxy[ind][1];
			}
		}

	}

	static boolean game(int x, int y, int ind) {
		int tx = x + dxy[ind][0];
		int ty = y + dxy[ind][1];
		if(!isRange(tx, ty)) return false;
		
		if(ind == 0) { // 悼率
			int tmp = dice[5];
			 dice[5] = dice[0];
			 dice[0] = dice[4];
			 dice[4] = dice[2];
			 dice[2] = tmp;
		}else if(ind == 1){ // 辑率
			int tmp = dice[4];
			 dice[4] = dice[0];
			 dice[0] = dice[5];
			 dice[5] = dice[2];
			 dice[2] = tmp;
		}else if(ind == 2) { // 合率
			 int tmp = dice[0];
			 dice[0] = dice[1];
			 dice[1] = dice[2];
			 dice[2] = dice[3];
			 dice[3] = tmp;
		}else if(ind == 3) { // 巢率
			int tmp = dice[3];
			 dice[3] = dice[2];
			 dice[2] = dice[1];
			 dice[1] = dice[0];
			 dice[0] = tmp;
		}
		
		if(map[tx][ty] == 0) {
			map[tx][ty] = dice[2];
		}else {
			dice[2] = map[tx][ty];
			map[tx][ty] = 0;
		}

		return true;
	}
	
	static boolean isRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
 	}

}
