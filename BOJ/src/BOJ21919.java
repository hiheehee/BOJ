import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ21919 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long result = 1;
		String str[] = br.readLine().split(" ");
		HashSet<Integer> hs = new HashSet<>();
		
		for(int i = 0; i < n; i++) {
			int temp = Integer.parseInt(str[i]);
			if(isSosu(temp)) {
				hs.add(temp);
			}
		}
		
		for(int i : hs) {
			result *= i;
		}
		
		System.out.println(result != 1 ? result:-1);
	}
	
	static boolean isSosu(long n) {
		if (n == 2) return true;
		for(int i = 2; i <= Math.sqrt(n); i++){
			if(n%i == 0) {
				return false;
			}
		}
		return true;
	}

}
