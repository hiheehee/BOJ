import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20061 {

	static int g[][] = new int[6][4];
	static int b[][] = new int[4][6];
	static int score = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			fill(t, x, y);
			boolean circle = IsLineAllSame();
			while(circle) circle = IsLineAllSame();
			IslightColor();
		}

		System.out.println(score);
		System.out.println(total());
	}
	
	static int total() {
		int sum = 0;
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				if(g[i][j] == 1) sum++;  
			}
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				if(b[i][j] == 1) sum++;
			}
		}
		
		return sum;
	}

	static void fill(int t, int x, int y) {
		int ig = 6;
		int ib = 6;
		
		if(t == 1) {
			for(int i = 0; i < 6; i++) {
				if(g[i][y] != 0) {
					ig = i;
					break;
				}
			}
			g[ig-1][y] = 1;
			
			for(int i = 0; i < 6; i++) {
				if(b[x][i] != 0) {
					ib = i;
					break;
				}
			}
			b[x][ib-1] = 1;
		}else if(t == 2) {
			for(int i = 0; i < 6; i++) {
				if(g[i][y] != 0 || g[i][y+1] != 0) {
					ig = i;
					break;
				}
			}
			g[ig-1][y] = 1;
			g[ig-1][y+1] = 1;
			
			for(int i = 0; i < 6; i++) {
				if(b[x][i] != 0) {
					ib = i;
					break;
				}
			}
			b[x][ib-1] = 1;
			b[x][ib-2] = 1;
		}else {
			for(int i = 0; i < 6; i++) {
				if(g[i][y] != 0) {
					ig = i;
					break;
				}
			}
			g[ig-1][y] = 1;
			g[ig-2][y] = 1;
			
			for(int i = 0; i < 6; i++) {
				if(b[x][i] != 0 || b[x+1][i] != 0) {
					ib = i;
					break;
				}
			}
			b[x][ib-1] = 1;
			b[x+1][ib-1] = 1;
		}
	}
	
	static void move(int d, int i) {
		if(d == 0) {
			for(int j = i; 0 < j; j--) {
				g[j][0] = g[j-1][0];
				g[j][1] = g[j-1][1];
				g[j][2] = g[j-1][2];
				g[j][3] = g[j-1][3];
				g[j-1][0] = g[j-1][1] = g[j-1][2] = g[j-1][3] = 0;
			}
		}else {
			for(int j = i; 0 < j; j--) {
				b[0][j] = b[0][j-1];
				b[1][j] = b[1][j-1];
				b[2][j] = b[2][j-1];
				b[3][j] = b[3][j-1];
				b[0][j-1] = b[1][j-1] = b[2][j-1] = b[3][j-1] = 0;
			}
		}
	}
	
	static boolean IsLineAllSame() {
		boolean check = false;
		for(int i = 0; i < 6; i++) {
			if(g[i][0] == 1 && g[i][1] == 1 && g[i][2] == 1 && g[i][3] == 1) {
				move(0, i);
				score++;
				check = true;
				break;
			}
		}
		
		for(int i = 0; i < 6; i++) {
			if(b[0][i] == 1 && b[1][i] == 1 && b[2][i] == 1 && b[3][i] == 1) {
				move(1, i);
				score++;
				check = true;
				break;
			}
		}
		
		return check;
	}
	
	static void IslightColor() {
		if(g[1][0] == 1 || g[1][1] == 1 || g[1][2] == 1 || g[1][3] == 1) move(0, 5);
		if(g[1][0] == 1 || g[1][1] == 1 || g[1][2] == 1 || g[1][3] == 1) move(0, 5);
		
		if(b[0][1] == 1 || b[1][1] == 1 || b[2][1] == 1 || b[3][1] == 1) move(1, 5);
		if(b[0][1] == 1 || b[1][1] == 1 || b[2][1] == 1 || b[3][1] == 1) move(1, 5);
	}
}
