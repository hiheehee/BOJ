import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ21611 {

	static int n, m;
	static ArrayList<Integer> al = new ArrayList<>();
	static ArrayList<String> marble = new ArrayList<>();
	static int map[][];
	static int magic[][];
	static int score[] = new int[4];
	static int dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}}; // ก่, ก้, ก็, กๆ
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		magic = new int[m][2];
		
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
			magic[i][0] = d;
			magic[i][1] = s;
		}
		
		map[n/2][n/2] = -1;
		init();
		location();
		for(int i = 0; i < m; i++) {
			destroy(magic[i][0], magic[i][1]);
			boolean check = bomb();
			while(check) check = bomb();
			change();
		}

		System.out.print(result());
	}
	
	static void init() {
		int d = 2;
		int s = 0;
		
		for(int i = 0; i < n/2; i++) {
			al.add(s);
			al.add(s+d);
			al.add(s+2*d);
			al.add(s+3*d);
			s += 4*d + 1;
			d += 2;
		}
	}
	
	static int result() {
		int sum = 0;
		for(int i = 1; i < 4; i++) {
			sum += i*score[i];
		}
		return sum;
	}
	
	static void change() {
		ArrayList<String> newMarble = new ArrayList<>();
		int count = 1;

		for(int i = 1; i < marble.size(); i++) {
			if(marble.get(i-1).equals(marble.get(i))) {
				count++;
			}else {
				newMarble.add(Integer.toString(count));
				newMarble.add(marble.get(i-1));
				count = 1;
			}
		}
		if(1 <= marble.size()) {
			newMarble.add(Integer.toString(count));
			newMarble.add(marble.get(marble.size()-1));
			marble = new ArrayList<>();
			int len = newMarble.size() <= n*n-1 ? newMarble.size() : n*n-1;
			for(int i = 0; i < len; i++) {
				marble.add(newMarble.get(i));
			}
		}
		
	}
	
	static boolean bomb() {
		int count = 1;
		boolean check = false;
		for(int i = marble.size()-2; -1 < i; i--) {
			if(marble.get(i).equals(marble.get(i+1))) {
				count++;
			}else {
				if(count >= 4) {
					score[Integer.parseInt(marble.get(i+1))] += count;
					for(int j = 0; j < count; j++) {
						marble.remove(i+1);
					}
					check = true;
				}
				count = 1;
			}
		}
		if(count >= 4) {
			score[Integer.parseInt(marble.get(0))] += count;
			for(int j = 0; j < count; j++) {
				marble.remove(0);
			}
			check = true;
		}
		return check;
	}

	static void location() {
		int tx = n/2;
		int ty = n/2;
		
		for(int i = 1; i < n; i++) {
			if(i%2 == 1) {
				for(int j = 0; j < i; j++) {
					int ttx = tx + dxy[2][0];
					int tty = ty + dxy[2][1];
					if(!isRange(ttx, tty)) return;
					if(map[ttx][tty] != 0) marble.add(Integer.toString(map[ttx][tty]));
					tx = ttx;
					ty = tty;
				}
				
				for(int j = 0; j < i; j++) {
					int ttx = tx + dxy[1][0];
					int tty = ty + dxy[1][1];
					if(!isRange(ttx, tty)) return;
					if(map[ttx][tty] != 0) marble.add(Integer.toString(map[ttx][tty]));
					tx = ttx;
					ty = tty;
				}
			}else {
				for(int j = 0; j < i; j++) {
					int ttx = tx + dxy[3][0];
					int tty = ty + dxy[3][1];
					if(!isRange(ttx, tty)) return;
					if(map[ttx][tty] != 0) marble.add(Integer.toString(map[ttx][tty]));
					tx = ttx;
					ty = tty;
				}
				
				for(int j = 0; j < i; j++) {
					int ttx = tx + dxy[0][0];
					int tty = ty + dxy[0][1];
					if(!isRange(ttx, tty)) return;
					if(map[ttx][tty] != 0) marble.add(Integer.toString(map[ttx][tty]));
					tx = ttx;
					ty = tty;
				}
			}
		}
	}
	
	static void destroy(int d, int s) {
		int count = 0;
		if (d == 0) count = 4*s-1;
		else if(d == 1) count = 4*s-3;
		else if(d == 2) count = 4*s-4;
		else if(d == 3) count = 4*s-2;

		for(int i = count; -1 < i; i -= 4) {
			if(marble.size() <= al.get(i)) continue;
			int index = al.get(i);
			marble.remove(index);
		}
	}
	
	static boolean isRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

}
