import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ1991 {
	static String result = "";
	static Map<String, List<String>> tree = new HashMap<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String str[] = br.readLine().split(" ");
			List<String> list = new ArrayList<>();
			
			list.add(str[1]);
			list.add(str[2]);
			tree.put(str[0], list);
			
		}
		
		preOrder("A");
		result += "\n";
		inOrder("A");
		result += "\n";
		postOrder("A");
		System.out.print(result);
	}
	
	// 전위 순회
	static void preOrder(String s) {
		if(s.equals(".")) {
			return;
		}
		
		result += s;
		preOrder(tree.get(s).get(0));
		preOrder(tree.get(s).get(1));
	}
	
	// 중위 순회
	static void inOrder(String s) {
		if(s.equals(".")) {
			return;
		}
		
		inOrder(tree.get(s).get(0));
		result += s;
		inOrder(tree.get(s).get(1));
	}
	
	// 후위 순회
	static void postOrder(String s) {
		if(s.equals(".")) {
			return;
		}
		
		postOrder(tree.get(s).get(0));
		postOrder(tree.get(s).get(1));
		result += s;
	}

}
