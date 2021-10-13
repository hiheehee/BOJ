import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1891 {

	static long X, Y, n;
	static char num[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		num = st.nextToken().toCharArray();
	
		st = new StringTokenizer(br.readLine());
		long dy = Long.parseLong(st.nextToken());
		long dx = Long.parseLong(st.nextToken());
		
		findLoc(0, 0, 0, (long) Math.pow(2, n), "");

		X -= dx;
		Y += dy;
		if(!IsRange(X, Y)) {
			System.out.print(-1);
		}else{
			findNum(X, Y, (long) Math.pow(2, n), "");
		}

	}
	
	static void findNum(long x, long y, long s, String idx) {
		if(s == 1) {
			System.out.println(idx);
			return;
		}
		
		long half = s/2;
		
		if (x < half && y >= half) findNum(x, y-s/2, s/2, idx+"1");
		else if(x < half && y < half) findNum(x, y, s/2, idx+"2");
		else if(x >= half && y < half) findNum(x-s/2, y, s/2, idx+"3");
		else findNum(x-s/2, y-s/2, s/2, idx+"4");
	}

	static void findLoc(int ind, long x, long y, long s, String idx) {		
		if(s == 1) {
			X = x;
			Y = y;
			return;
		}
		if(num[ind] == '1') findLoc(ind+1, x, y+s/2, s/2, idx+"1");
		else if(num[ind] == '2') findLoc(ind+1, x, y, s/2, idx+"2");
		else if(num[ind] == '3') findLoc(ind+1, x+s/2, y, s/2, idx+"3");
		else if(num[ind] == '4') findLoc(ind+1, x+s/2, y+s/2, s/2, idx+"4");
	}
	
	static boolean IsRange(long x, long y) {
		return 0 <= x && x < Math.pow(2, n) && 0 <= y && y < Math.pow(2, n);
	}
	
}
