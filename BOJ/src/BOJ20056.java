import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056 {

	static int n, m;
	static String map[][];
	static int dxy[][] = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new String[n][n];
		
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++)
				map[i][j] = "";
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c] += m+" "+s+" "+d+"-"; 
		}
		
		while(k-- > 0) {
			move();
			bomb();
		}
		result();
	}

	static void result() {
		int sum = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!map[i][j].equals("")) {
					String fireballs[] = map[i][j].split("-");
					for(int k = 0; k < fireballs.length; k++) {
						String fireball[] = fireballs[k].split(" ");
						int m = Integer.parseInt(fireball[0]);
						
						sum += m;
					}
				}
			}
		}
		System.out.println(sum);
	}
	
	static void copy(String temp[][]) {
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++)
				map[i][j] = temp[i][j];
	}
	
	static void move() {
		String temp[][] = new String[n][n];
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++)
				temp[i][j] = "";
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!map[i][j].equals("")) {
					String fireballs[] = map[i][j].split("-");
					for(int k = 0; k < fireballs.length; k++) {
						String fireball[] = fireballs[k].split(" ");
						int m = Integer.parseInt(fireball[0]);
						int s = Integer.parseInt(fireball[1]);
						int d = Integer.parseInt(fireball[2]);
						
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
						
						temp[tx][ty] += m+" "+s+" "+d+"-";
					}
				}
			}
		}
		
		copy(temp);
	}
	
	static void bomb() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!map[i][j].equals("")) {
					String fireballs[] = map[i][j].split("-");
					if(fireballs.length < 2) continue;
					int M = 0;
					int S = 0;
					boolean isEven = false;
					boolean isOdd = false;
					
					for(int k = 0; k < fireballs.length; k++) {
						String fireball[] = fireballs[k].split(" ");
						int m = Integer.parseInt(fireball[0]);
						int s = Integer.parseInt(fireball[1]);
						int d = Integer.parseInt(fireball[2]);
						
						M += m;
						S += s;
						if(d%2 == 0) isEven = true;
						else isOdd = true;
					}
					
					M /= 5;
					S /= fireballs.length;
					map[i][j] = "";
					if(M == 0) continue;
					
					int ind = 0;
					if(isEven && isOdd) ind = 1;
					
					for(; ind < 8; ind += 2) {
						map[i][j] += M+" "+S+" "+ind+"-";
					}
				}
			}
		}
	}
	
	static boolean isRange(int x, int y) {
		return -1 < x && x < n && -1 < y && y < n;
	}
}
