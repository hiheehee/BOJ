import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17837 {

	static int n, k;
	static int map[][];
	static int dir[][] = {{0,1},{0,-1},{-1,0},{1,0}}; // ¡æ, ¡ç, ¡è, ¡é
	static int loc[][];
	static String init[][];
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		loc = new int[k][3];
		init = new String[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				init[i][j] = ".";
			}
		}
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			loc[i][0] = r;
			loc[i][1] = c;
			loc[i][2] = d;
			init[r][c] = Integer.toString(i)+" ";
		}
		
		while(++result <= 1000) {
			if(game()) break;
		}
		System.out.println(result > 1000? -1 : result);
	}
	
	static boolean game() {
		for(int i = 0; i < k; i++) {
			int tx = loc[i][0] + dir[loc[i][2]][0];
			int ty = loc[i][1] + dir[loc[i][2]][1];
			int D = 0;
			
			if(loc[i][2] == 0) {
				D = 1;
			}else if(loc[i][2] == 1) {
				D = 0;
			}else if(loc[i][2] == 2) {
				D = 3;
			}else {
				D = 2;
			}
			
			int ttx = loc[i][0] + dir[D][0];
			int tty = loc[i][1] + dir[D][1];
			
			if(!isRange(tx, ty)) {
				if(!isRange(ttx, tty)) {
					loc[i][2] = D;
					continue;
				}else if(isRange(ttx, tty) && map[ttx][tty] == 2) {
					loc[i][2] = D;
					continue;
				}
				tx = ttx;
				ty = tty;
				loc[i][2] = D;
			}else if(map[tx][ty] == 2) {
				if(!isRange(ttx, tty)) {
					loc[i][2] = D;
					continue;
				}else if(isRange(ttx, tty) && map[ttx][tty] == 2) {
					loc[i][2] = D;
					continue;
				}
				tx = ttx;
				ty = tty;
				loc[i][2] = D;
			}
			
			if(map[tx][ty] == 0) { // Èò»ö
				String ind[] = init[loc[i][0]][loc[i][1]].split(" ");
				int check = 0;
				if(0 < init[tx][ty].length() && init[tx][ty].charAt(0) == '.') init[tx][ty] = "";
				init[loc[i][0]][loc[i][1]] = "";
				for(int j = 0; j < ind.length; j++) {
					if(ind[j].equals(Integer.toString(i))) check = j;
				}
				
				for(int j = 0; j < check; j++) {
					init[loc[i][0]][loc[i][1]] += ind[j]+" ";
				}
				
				for(int j = check; j < ind.length; j++) {
					init[tx][ty] += ind[j]+" ";
					int index = Integer.parseInt(ind[j]);
					loc[index][0] = tx;
					loc[index][1] = ty;
				}
				if(init[tx][ty].length() == 0) init[tx][ty] = ".";
				String count[] = init[tx][ty].split(" ");
				if(count.length >= 4) {
					return true;
				}
			}else if(map[tx][ty] == 1) { // »¡°£»ö
				String ind[] = init[loc[i][0]][loc[i][1]].split(" ");
				
				if(0 < init[tx][ty].length() && init[tx][ty].charAt(0) == '.') init[tx][ty] = "";
				init[loc[i][0]][loc[i][1]] = "";
				int check = 0;
				for(int j = 0; j < ind.length; j++) {
					if(ind[j].equals(Integer.toString(i))) {
						check = j;
						break;
					}
					else init[loc[i][0]][loc[i][1]] += ind[j]+" ";
				}
				
				for(int j = ind.length-1; check <= j; j--) {
					init[tx][ty] += ind[j]+" ";
					int index = Integer.parseInt(ind[j]);
					loc[index][0] = tx;
					loc[index][1] = ty;
				}
				if(init[tx][ty].length() == 0) init[tx][ty] = ".";
				String count[] = init[tx][ty].split(" ");
				if(count.length >= 4) {
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean isRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}
}
