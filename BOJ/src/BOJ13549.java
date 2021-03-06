import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549 {
	static int seconds[] = new int[100001];
	static String dx[] = { "2 0", "1 -1", "1 1"};
	static boolean visited[] = new boolean[100001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		if(k <= n) {
			System.out.println(n-k);
		}else {
			bfs(n,k);
			System.out.println(seconds[k]);
		}
		
		

	}

	static void bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		seconds[start] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 0; i < 3; i++) {
				String str[] = dx[i].split(" ");
				int x = Integer.parseInt(str[0])*cur + Integer.parseInt(str[1])*1;
				
				if(x < 0 || 100000 < x) {
					continue;
				}
				
				if(!visited[x]) {
					seconds[x] = seconds[cur] + Math.abs(Integer.parseInt(str[1]));
					q.add(x);
					visited[x] = true;
				}

			}
		}

	}
	
}
