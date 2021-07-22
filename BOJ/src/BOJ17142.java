import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17142 {

	static int dxy[][] = {{-1,0},{0,-1},{1,0},{0,1}};
	static boolean visited[][];
	static int min = Integer.MAX_VALUE;
	static int total = 0;
	static int n, m;
	static PriorityQueue<coordinate> pq;
	static ArrayList<coordinate> virus = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if(cur == 1) { // 벽
					visited[i][j] = true;
					total++;
					continue;
				} else if(cur == 2) virus.add(new coordinate(i, j, 0)); // 바이러스
			}
		}
		
		total = n*n - total - m;
		combination(virus.size()); 
		if(min != Integer.MAX_VALUE)System.out.println(min);
		else System.out.println(-1);
	}
	
	static void combination(int s) {
		for(int i = 0; i < (1 << s); i++) {
			if(Integer.bitCount(i) == m) {
				String digit = "%"+s+"s"; 
				String str = String.format(digit, Integer.toBinaryString(i)).replace(" ", "0");
				bfs(str);
			}
		}
	}
	
	static void bfs(String s) {
		boolean v[][] = new boolean[n][n];
	    
        for(int i = 0; i < n; i++){
            System.arraycopy(visited[i], 0, v[i], 0, n);
        }
        
		int map[][] = new int[n][n];
		String str[] = s.split("");
		pq = new PriorityQueue<>();
		int max = 0;
		int len = 0;
		
		for(int i = 0; i < str.length; i++) {
			if(str[i].equals("1")) {
				pq.add(new coordinate(virus.get(i).x, virus.get(i).y, 0));
			}else map[virus.get(i).x][virus.get(i).y] = -1;
		}
		
		while(!pq.isEmpty()) {
			coordinate cur = pq.poll();
			if(v[cur.x][cur.y]) continue;
			if(map[cur.x][cur.y] != -1) max = Math.max(max, cur.count);
			if(cur.count != 0) len++;
			v[cur.x][cur.y] = true;
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dxy[i][0];
				int ty = cur.y + dxy[i][1];
				if(tx < 0 || n <= tx || ty < 0 || n <= ty) continue;
				if(v[tx][ty]) continue;
				pq.add(new coordinate(tx, ty, cur.count+1));
			}
		}
		if(len != total) return;
		min = Math.min(min, max);
		
	}
	
	static class coordinate implements Comparable<coordinate>{
		int x;
		int y;
		int count;
		
		coordinate (int x, int y, int count){
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public int compareTo(coordinate o) {
			return count - o.count;
		}
	}

}
