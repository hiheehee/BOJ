import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138 {
	static char before[][];
	static char after[];
	static int n;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		before = new char[2][n];
		before[0] = br.readLine().toCharArray();
		after = br.readLine().toCharArray();

		for(int i = 0; i < n; i++) {
			before[1][i] = before[0][i];
		}
		
		for (int i = 0; i < n; i++) {
            System.out.print(before[0][i]);
        }
		System.out.println("---------------");
		for (int i = 0; i < n; i++) {
            System.out.print(before[1][i]);
        }
		bulb(0, 1, 0);
		
		turnOnOff(1, 0);
		bulb(1, 1, 1);
	
		System.out.print(result==Integer.MAX_VALUE?-1:result);
	}
	
	static void turnOnOff(int cur, int ind) {
		for(int i = ind-1; i < ind+2; i++) {
			if(-1 < i && i < n) {
				before[cur][i] = before[cur][i]=='X'?'O':'X';
			}
		}
	}
	
	static void bulb(int cur, int ind, int count) {
		if(ind == n) {
			if(before[cur][ind-1] == after[ind-1]) result = Math.min(result, count);
		}else {
			if(before[cur][ind-1] != after[ind-1]) {
				turnOnOff(cur, ind);
				bulb(cur, ind+1, count+1);
			}else {
				bulb(cur, ind+1, count);
			}
			/*
			for (int i = 0; i < n; i++) {
	            System.out.print(before[cur][i]);
	        }
	        System.out.println();
	        System.out.println("---------------");*/
		}
	}

}
