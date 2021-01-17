import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20528 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String str[] = br.readLine().split(" ");
		boolean check = true;
		
		for(int i = 1; i < n; i++) {
			if(str[i-1].charAt(0) != str[i].charAt(0)) {
				check = false;
				break;
			}
		}

		int result = check ? 1 : 0;
		System.out.println(result);
		

	}
}