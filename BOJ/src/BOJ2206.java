import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2206 {

    static int visited[][];
    static int X[] = {-1,1,0,0};
    static int Y[] = {0,0,-1,1};
    static int map[][];
    static int n, m;
    static int answer = Integer.MAX_VALUE;
    static Queue<coordinate> q = new LinkedList<>();
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
        visited = new int[n][m];
        map = new int[n][m];
        
        for(int i = 0; i<n; i++) {
        	String temp[] = scanner.next().split("");
        	for(int j = 0 ; j<m; j++) {
        		map[i][j] = Integer.parseInt(temp[j]);
        		visited[i][j] = Integer.MAX_VALUE;
        	}
        }
        
        bfs(0,0);
        
        if(answer == Integer.MAX_VALUE) { // 이동하지 못하는 경우
        	System.out.println(-1);
        }else { // 이동한 경우 최단경로 출력
        	System.out.println(answer);
        }
        
	}
	private static void bfs(int a, int b) {
		q.add(new coordinate(a,b,1,0));
		visited[a][b] = 0; 
		
		while(!q.isEmpty()) {
			coordinate temp = q.poll();
			
			if(temp.x == n-1 && temp.y == m-1) { // [n,m] 위치에 도착한 경우 return
				answer = temp.dis;
				break;
			}
			
			for(int i = 0; i < 4; i++) { // 상 하 좌 우 이동
				int x = temp.x + X[i];
				int y = temp.y + Y[i];
				
				if(x < 0 || y < 0 || x >= n || y >= m ) { //범위 초과시 무시
					continue;
				}
				
				if(visited[x][y] <= temp.count) { //이동한 적있는 칸은 무시
					continue;
				}
				
				
				if(map[x][y] == 0) { // 벽이 아니면 이동 가능
					visited[x][y] = temp.count;
					q.add(new coordinate(x, y, temp.dis+1, temp.count));		
				}else{ // 벽인 경우
					if(temp.count == 0) { // 벽을 한번도 부수지 않은 경우 이동 가능 
						visited[x][y] = temp.count+1;
						q.add(new coordinate(x, y, temp.dis+1, temp.count+1));	
					}
				}

			}
			
		}
		
	}
	
	static class coordinate {
		int x; // 행
		int y; // 열
		int dis; // 이동한 거리
		int count; //부신 벽의 수
		
		public coordinate(int x, int y, int dis, int count) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.count = count;
		}
		
	}
}
