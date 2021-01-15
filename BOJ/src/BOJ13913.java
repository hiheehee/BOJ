import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ13913 {
	static int seconds[] = new int[100001];
	static String dx[] = {"1 1", "1 -1", "2 0"};
	static boolean visited[] = new boolean[100001];
	static int parents[] = new int[100001];
	
	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		bfs(n, k);

	}
	
	static void bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == end) {
				System.out.println(seconds[end]);
				int index = end;
				Stack<Integer> st = new Stack<>();
				st.push(end);
				
				while(index != start) {
					st.push(parents[index]);
					index = parents[index]; 
				}
				
				while(!st.isEmpty()) {
					System.out.print(st.pop()+" ");
				}
				
				return;
			}
			
			for(int i = 0; i < 3; i++) {
				String num[] = dx[i].split(" ");
				int x = Integer.parseInt(num[0]) * cur + Integer.parseInt(num[1]); 
				
				if(x < 0 || 100000 < x) {
					continue;
				}
			
				if(!visited[x]) {
					visited[x] = true;
					seconds[x] = seconds[cur] + 1;
					q.offer(x);
					parents[x] = cur;
				}
				
			}
		}
		return;
	}

}