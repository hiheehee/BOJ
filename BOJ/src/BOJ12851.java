import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851 {
	static int count = 0;
	static int seconds[] = new int[100001];
	static String dx[] = {"1 1", "1 -1", "2 0"};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		if(k <= n) {
			System.out.println(n-k);
			System.out.print(1);
		}else {
			bfs(n,k);
			System.out.println(seconds[k]);
			System.out.print(count);
		}
		
	}
	
	static void bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		int check = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(check < seconds[cur]) {
				return;
			}

			for(int i = 0; i < 3; i++) {
				String str[] = dx[i].split(" ");
				int x = Integer.parseInt(str[0])*cur + Integer.parseInt(str[1])*1;
				
				if(x < 0 || 100000 < x) {
					continue;
				}
				
				if(x == end) {
					count++;
					check = seconds[cur];
				}
				
				if(seconds[x] == 0 || seconds[x] == seconds[cur] + 1) {
					q.add(x);
					seconds[x] = seconds[cur] + 1;
				}
			}
		}

	}

}
