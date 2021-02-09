import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ5052 {
	static boolean check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			check = true;
			int n = Integer.parseInt(br.readLine());
			Node root = new Node();
			String words[] = new String[n];
			
			for(int i = 0; i < n; i++) {
				words[i] = br.readLine();
			}
			
			for(String s : words) { 
				insert(root, s); 
			}
			
			for(String s : words) { 
				check(root, s);
				if(!check) {
					break;
				}
			}
			
			if(check) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}

	}
		
	static void check(Node cur, String s) {
		char str[] = s.toCharArray();
		for(int i = 0; i < str.length; i++) {
			cur = cur.children.get(str[i]);
			if(i != str.length-1 && cur.check) {
				check = false;
				return;
			}
		}
	}
	
	static void insert(Node cur, String s) {
		char str[] = s.toCharArray();
		for(int i = 0; i < str.length; i++) {
			cur = cur.children.computeIfAbsent(str[i], l -> new Node());
			if(i == str.length-1) {
				cur.check = true;
			}
		}
	}
	
	static class Node{
		Map<Character, Node> children;
		boolean check;
		
		Node(){
			children = new HashMap<>();
		}
	}

}
