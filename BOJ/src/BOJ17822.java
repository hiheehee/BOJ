import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17822 {

	static int dart[][];
	static int n, m, t;
	static int dxy[][] = {{1,0},{-1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		dart = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				dart[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(d == 0) {
				while(k-- > 0) {
					clock(x, k);
				}
				
			}else {
				while(k-- > 0) {
					banClock(x, k);
				}
			}

			if(!cal1()) cal2();
		}
		System.out.println(result());
		
	}

	static int result() {
		int sum = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(dart[i][j] != 0) {
					sum += dart[i][j];
				}
			}
		}
		return sum;
	}
	
	static boolean cal1() {
		boolean check = false;
		boolean isSame[][] = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(dart[i][j] == 0) continue;
				
				for(int k = 0; k < 4; k++) {
					int tx = i + dxy[k][0];
					int ty = j + dxy[k][1];
					
					if(isRange(tx, ty)) {
						if(dart[i][j] == dart[tx][ty]) {
							isSame[i][j] = true;
							isSame[tx][ty] = true;
							check = true;
						}
					}else {
						if(tx < 0 || n <= tx) continue;
						
						if(ty < 0) {
							ty = m-1;
						}else if(m <= ty) {
							ty = 0;
						}
						
						if(dart[i][j] == dart[tx][ty]) {
							isSame[i][j] = true;
							isSame[tx][ty] = true;
							check = true;
						}
					}
				}
			}
		}
		
		if(!check) return check;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(isSame[i][j]) {
					dart[i][j] = 0;
				}
			}
		}
		
		return check;
	}
	
	static boolean isRange(int x, int y) {
		return -1 < x &&  x < n && -1 < y && y < m;
	}
	
	static void cal2() {
		int count = 0;
		double avg = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(dart[i][j] != 0) {
					count++;
					avg += dart[i][j];
				}
			}
		}
        if(count == 0) return;
		avg /= (double)count;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(dart[i][j] != 0) {
					if(dart[i][j] > avg) {
						dart[i][j]--;
					}else if(dart[i][j] < avg){
						dart[i][j]++;
					}
				}
			}
		}
	}
	
	static void clock(int x, int k) {
		
		for(int i = x-1; i < n; i+= x) {
			int init = dart[i][m-1];
			for(int j = m-1; 0 < j; j--) {
				dart[i][j] = dart[i][j-1];
			}
			dart[i][0] = init;
		}
	}
	
	static void banClock(int x, int k) {
		for(int i = x-1; i < n; i+= x) {
			int init = dart[i][0];
			for(int j = 0; j < m-1; j++) {
				dart[i][j] = dart[i][j+1];
			}
			dart[i][m-1] = init;
		}
	}

}
