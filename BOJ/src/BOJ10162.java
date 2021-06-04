import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10162 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int first = t;
		int a = 300;
		int b = 60;
		int c = 10;
		int count[] = new int[3];
		
		if(a <= t) {
			count[0] = t/a;
			t %= a;
		}
		if(b <= t) {
			count[1] = t/b;
			t %= b;
		}
		count[2] = t/c;
		
		if(count[0]*a + count[1]*b + count[2]*c == first) {
			System.out.print(count[0]+" "+count[1]+" "+count[2]);
		}else {
			System.out.print(-1);
		}
	}

}
