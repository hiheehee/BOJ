import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String str[] = br.readLine().split(" ");
		int s = 0;
		int e = 0;
		int sum = 0;
		int result = 0;
		
		while(true) {
			if(m <= sum) {
				sum -= Integer.parseInt(str[s++]);
			}else if(e == n) {
				break;
			}else {
				sum += Integer.parseInt(str[e++]);	
			}
			if(sum == m) result++;
		}
		
		System.out.print(result);
		
	}

}
