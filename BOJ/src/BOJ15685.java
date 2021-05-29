import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ15685 {
	static boolean visited[][] = new boolean[101][101];
	static int dxy[][] = {{0,1}, {-1,0}, {0,-1}, {1,0}};  // กๆ - 0, ก่ - 1, ก็ - 2, ก้ - 3
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		
		for(int i = 0; i < n; i++) {
			String str[] = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			int d = Integer.parseInt(str[2]);
			int g = Integer.parseInt(str[3]);
			dragon(y, x, d, g);
		}
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) {
					answer++;
				}
			}
		}

		System.out.println(answer);

	}
	
	static void dragon(int x, int y, int d, int g) {
		coordinate end = new coordinate(x, y);
		ArrayList<Integer> dir = new ArrayList<>();
		dir.add(d);
		visited[x][y] = true;
		
		for(int i = 0; i < g+1; i++) {
			for(int j = dir.size()/2; j < dir.size(); j++) {
				end.x = end.x + dxy[dir.get(j)][0];
				end.y = end.y + dxy[dir.get(j)][1];
				visited[end.x][end.y] = true;
			}

			ArrayList<Integer> temp = new ArrayList<>();
			for(int j = dir.size()-1; j > -1; j--) {
				temp.add((dir.get(j)+1)%4);
			}
			
			for(int j : temp) {
				dir.add(j);
			}
		}
		
	}
	
	static class coordinate {
		int x;
		int y;
		
		coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
