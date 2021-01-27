import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10986 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 합을 나누는 수
		int num[] = new int[n];
		long sum[] = new long[n+1];
		long count = 0;

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < n+1; i++) {
			sum[i] = Integer.parseInt(st.nextToken());
			sum[i] += sum[i-1];
		}

		for(int i = 1; i < n+1; i++) {
			for(int j = i; j < n+1; j++) {
				if((sum[i] - sum[j-1])%m == 0) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}

}