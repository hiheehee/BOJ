import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991 {
	static int n;
	static int m;
	static int graph[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static char map[][];
	static int result = Integer.MAX_VALUE;
	static ArrayList<coordinate> dust;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 열
		m = Integer.parseInt(st.nextToken()); // 행

		while(n != 0 && m != 0) {
			int x = 0;
			int y = 0;
			dust = new ArrayList<>();
			map = new char[m][n];
			result = Integer.MAX_VALUE;
			
			for(int i = 0; i < m; i++){
				char str[] = br.readLine().toCharArray();
				for(int j = 0; j < n; j++) {
					if(str[j] == 'o') {
						dust.add(0, new coordinate(i, j));
					}else if(str[j] == '*') {
						dust.add(new coordinate(i, j));
					}
					
					if(str[j] == 'x') {
						map[i][j] = 'x';
					}else {
						map[i][j] = '.';
					}
				}
			}
			
			graph = new int[dust.size()][dust.size()];

			for(int i = 0; i < dust.size(); i++){
				for(int j = i+1; j < dust.size(); j++) {
					int len = bfs(dust.get(i), dust.get(j));
					graph[i][j] = graph[j][i] = len;
					
					if(i == 0 && len == -1) {
						result = -1;
						break;
					}
				}
				if(result == -1) {
					break;
				}
			}

			if(result != -1) {
				boolean[] visited = new boolean[dust.size()+1];
				dfs("", visited, 0);
			}
			
			System.out.println(result);
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 열
			m = Integer.parseInt(st.nextToken()); // 행
		}
	}
	
	static void dfs(String output, boolean[] visited, int depth) {
		if(depth == dust.size()-1) {
			searchMinPath(output);
		}
		
		if(result == -1) {
			return;
		}
		
		for(int i = 1; i < dust.size(); i++) {
			if(visited[i] != true) {
				visited[i] = true;
				dfs(output+i+" ", visited, depth+1);
				visited[i]= false;
			}
		}
	}
	
	static void searchMinPath(String path) {
		String str[] = path.split(" ");
		int temp = 0; 
		int index = 0;
		
		for(int i = 0; i < str.length; i++) {
			if(graph[index][Integer.parseInt(str[i])] != -1) {
				temp += graph[index][Integer.parseInt(str[i])];
				index = Integer.parseInt(str[i]);
			}else if (graph[index][Integer.parseInt(str[i])] == -1){
				result = -1;
				return;
			}
		}
		
		if(temp < result) {
			result = temp;
		}
	}
	
	static int bfs(coordinate start, coordinate end) {
		Queue<coordinate> q = new LinkedList<>();
		q.offer(start);
		int visited[][] = new int[m][n];
		
		for(int i = 0; i < m; i++) {
			Arrays.fill(visited[i], -1);
		}
		
		visited[start.x][start.y] = 0;
		
		while(!q.isEmpty()) {
			coordinate cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int x = cur.x + dx[i];
				int y = cur.y + dy[i];
				
				if(x < 0 || m <= x || y < 0 || n <= y) {
					continue;
				}
				
				if(visited[x][y] != -1 || map[x][y] == 'x') {
					continue;
				}
				
				if(x == end.x && y == end.y) {
					return visited[cur.x][cur.y] + 1;
				}
				
				visited[x][y] = visited[cur.x][cur.y] + 1;
				q.offer(new coordinate(x, y));
				
			}
		}
		
		return -1;
	}
	
	static class edge implements Comparable<edge>{
		int node;
		int len;
		
		edge(int node, int len){
			this.node = node;
			this.len = len;
		}

		@Override
		public int compareTo(edge o) {
			return len - o.len;
		}
		
		
	}
	
	static class coordinate {
		int x;
		int y;
		
		coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
