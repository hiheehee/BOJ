import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096 {

	static int score[][][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		score = new int[n][3][3];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				score[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 3; i++) {
			score[0][i][2] = score[0][i][1] = score[0][i][0];
		}
		
		for(int i = 1; i < n; i++) {
			score[i][0][1] = Math.min(score[i-1][0][1], score[i-1][1][1]) + score[i][0][0];
			score[i][1][1] = Math.min(Math.min(score[i-1][0][1], score[i-1][1][1]), score[i-1][2][1]) + score[i][1][0];
			score[i][2][1] = Math.min(score[i-1][1][1], score[i-1][2][1]) + score[i][2][0];
			
			score[i][0][2] = Math.max(score[i-1][0][2], score[i-1][1][2]) + score[i][0][0];
			score[i][1][2] = Math.max(Math.max(score[i-1][0][2], score[i-1][1][2]), score[i-1][2][2]) + score[i][1][0];
			score[i][2][2] = Math.max(score[i-1][1][2], score[i-1][2][2]) + score[i][2][0];
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			max = Math.max(max, score[n-1][i][2]);
			min = Math.min(min, score[n-1][i][1]);
		}
		
		System.out.println(max+" "+min);
	}

}
