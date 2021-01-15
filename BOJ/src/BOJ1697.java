import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697 {
	static int min;
    static boolean visited[];
    static int num[];
    static int X[] = {-1,1,2};
    static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		visited = new boolean[100001];
		num = new int[100001];
        bfs(n,m);
		
	}
	private static void bfs(int start, int end) {
		q.add(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == end) {
					System.out.println(num[end]);
					return;
			}
			
			for(int i = 0; i < 3; i++) {
				int x = 0;
				
				if(i == 2) {
				    x = cur * X[i];
			    }else {
			    	x = cur + X[i];
			    }
			
				
				if(x < 0 || 100000 < x) {
					continue;
				}
					
				if(!visited[x]) {
					q.add(x);
					num[x] = num[cur] + 1;
					visited[x] = true;
					if(x == end) {
						System.out.println(num[end]);
						return;
					}
				}
				
				
			}
		}
	}

}