import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12871 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		String S = s;
		String T = t;

		if(s.length() != t.length()) {
			int len = LCM(s.length(), t.length());

			while(S.length() != len) {
				S += s;
			}
			
			while(T.length() != len) {
				T += t;
			}
		}
		
		if(S.equals(T)) System.out.print(1);
		else System.out.print(0);

	}
	
	static int GCD(int a, int b) {
		while(b > 0) {
			int temp = a;
			a = b;
			b = temp%b;
		}
		return a;
	}
	
	static int LCM(int a, int b) {
		return (a*b)/GCD(a, b);
	}

}
