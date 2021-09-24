import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ17143 {

	static String sea[][];
	static int dxy[][] = {{-1,0},{1,0},{0,1},{0,-1}};
	static int R, C;
	static int eat = 0;
	static HashMap<String, String> hm = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		sea = new String[R][C];
		int ind = -1;
		
		for(int i = 0; i < R; i++) 
			for(int j = 0; j < C; j++) 
				sea[i][j] = "";
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			String z = st.nextToken();
			
			sea[r][c] += z;
			hm.put(z, s+" "+d);
		}

		while(ind++ < C-1) {
			Eat(ind);
			move();
		}
		
		System.out.print(eat);
	}
	
	static void Eat(int i) {
		for(int j = 0; j < R; j++) {
			if(!sea[j][i].equals("")) {
				eat += Integer.parseInt(sea[j][i]);
				sea[j][i] = "";
				return;
			}
		}
	}
	
	static boolean isRange(int x, int y) {
		return -1 < x &&  x < R && -1 < y && y < C;
	}
	
	static void move() {
		String temp[][] = new String[R][C];
		
		for(int i = 0; i < R; i++) 
			for(int j = 0; j < C; j++) 
				temp[i][j] = "";
		
		for(int i = 0; i < R; i++) {			
			for(int j = 0; j < C; j++) {
				if(!sea[i][j].equals("")) {
					String sd[] = hm.get(sea[i][j]).split(" ");
					int s = Integer.parseInt(sd[0]);
					int d = Integer.parseInt(sd[1]);
					int tx = i + dxy[d][0] * s;
					int ty = j + dxy[d][1] * s;
					
					if(isRange(tx, ty)) {
						temp[tx][ty] += sea[i][j]+" ";
					}else {
						int c = s%((R-1)*2);
						if(!isRange(0, ty)) c = s%((C-1)*2);
						tx = i;
						ty = j;
						while(c-- > 0) {
							tx += dxy[d][0];
							ty += dxy[d][1];
							
							if(!isRange(tx, ty)) {
								if(d == 0) {
									d = 1;
								}else if(d == 1) {
									d = 0;
								}else if(d == 2) {
									d = 3;
								}else if(d == 3) {
									d = 2;
								}
								tx += dxy[d][0]*2;
								ty += dxy[d][1]*2;
							}
						}
						temp[tx][ty] += sea[i][j]+" ";

						hm.put(sea[i][j], s+" "+d);
					}
				}
			}
		}
		
		for(int i = 0; i < R; i++) {			
			for(int j = 0; j < C; j++) {
				if(!temp[i][j].equals("")) {
					String s[] = temp[i][j].split(" ");
					int max = 0;
					for(int k = 0; k < s.length; k++) {
						if (!s[k].equals("")) max = Math.max(max, Integer.parseInt(s[k]));
					}
					
					sea[i][j] = Integer.toString(max);
				}else {
					sea[i][j] = "";
				}
			}
		}

	}
	
}
