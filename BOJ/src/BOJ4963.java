import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4963 {
	static int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int island[][];
	static boolean visited[][];
	static String result = "";
	static int count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		while(!str.equals("0 0")) {
			String size[] = str.split(" ");
			int w = Integer.parseInt(size[0]);
			int h = Integer.parseInt(size[1]);
			island = new int[h][w];
			visited = new boolean[h][w];
			count = 0;
			
			for(int i = 0; i < h; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					island[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(island[i][j] == 1 && !visited[i][j]) {
						count += searchIsland(i,j);
					}
				}
			}
			
			result += count+"\n";
			str = br.readLine();
		}
		
		System.out.println(result);
	}
	
	static int searchIsland(int x, int y) {
		Queue<Integer> qX = new LinkedList<>();
		Queue<Integer> qY = new LinkedList<>();
		qX.offer(x);
		qY.offer(y);
		visited[x][y] = true;
		
		while(!qX.isEmpty() && !qY.isEmpty()) {
			int cX = qX.poll();
			int cY = qY.poll();
			for(int i = 0; i < 8; i++) {
				int tX = cX + dx[i];
				int tY = cY + dy[i];
				
				if(tX < 0 || island.length <= tX || tY < 0 || island[0].length <= tY) {
					continue;
				}
				
				if(island[tX][tY] == 1 && !visited[tX][tY]) {
					visited[tX][tY] = true;
					qX.offer(tX);
					qY.offer(tY);
				}
			}
		}
		
		return 1;
		
	}

}
