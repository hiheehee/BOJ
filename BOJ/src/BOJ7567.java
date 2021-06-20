import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ7567 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split("");
		Stack<String> st = new Stack<>();
		int result = 0;
		for(String s : str) {
			if(st.isEmpty()) {
				st.add(s);
				result+=10;
			}else {
				if(st.peek().equals(s)) {
					result+=5;
				}else {
					result+=10;
				}
				st.add(s);
			}
		}
		System.out.println(result);
	}

}
