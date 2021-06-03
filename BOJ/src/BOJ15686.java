import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686 {
	static ArrayList<coordinate> city = new ArrayList<>();
    static ArrayList<coordinate> chicken = new ArrayList<>();
    static ArrayList<coordinate>[][] house;
    static int answer = Integer.MAX_VALUE;
    static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		house = new ArrayList[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				house[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int tmpcity = Integer.parseInt(st.nextToken());
				if (tmpcity == 2) chicken.add(new coordinate(i, j));
				if (tmpcity == 1) city.add(new coordinate(i, j)); 
			}
		}
		
		for(int i = 1; i < m+1; i++) {
			combination(chicken.size(), i);
		}

		System.out.println(answer);
	}
	
	static void combination(int n, int d) {
		for(int i = 0; i < (1<<n); i++) {
			if(Integer.bitCount(i) == d) {
				String digit = "%"+n+"s";
				String str = String.format(digit, Integer.toBinaryString(i)).replace(" ", "0");
				cal(str);
			}
		}
	}
	
	static void cal(String str) {
		String visited[] = str.split("");

		int result = 0;
		for(int i = 0; i < city.size(); i++) {
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < chicken.size(); j++) {
				if(visited[j].equals("1")) {
					min = Math.min(min, Math.abs(city.get(i).x- chicken.get(j).x)+Math.abs(city.get(i).y- chicken.get(j).y));
				}
			}
			result += min;
			if(result > answer) {
				return;
			}
		}
		answer = Math.min(answer, result);
	}
	
	static class coordinate{
		int x;
		int y;
		
		public coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
