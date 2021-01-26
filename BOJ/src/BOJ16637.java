import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ16637 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char str[] = br.readLine().toCharArray();
		Stack<Character> operation = new Stack<>();
		Stack<Integer> num = new Stack<>();
		
		for(int i = 0; i < n; i++) {
			if(str[i] == '*') {
				int a = num.pop();
				int b = num.pop();
				
				num.add(a*b);
			}else if (str[i] == '+'){
				int a = num.pop();
				int b = num.pop();
				
				num.add(a+b);
			}else if(str[i] == '-') {
				int a = num.pop();
				int b = num.pop();
				
				num.add(a*b);
			}else {
				
			}
		}
		
		
	}

}
