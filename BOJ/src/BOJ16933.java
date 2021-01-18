import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16933 {
	static int n;
	static int m;
	static int k;
	static char map[][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {-1,1,0,0};
	static int visited[][][];
	static int result = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new int[n][m][k+1];

		for(int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		bfs();
		
		System.out.println(result);
		
	}
	
	static void bfs() {
		PriorityQueue<coordinate> q = new PriorityQueue<>();
		q.offer(new coordinate(0,0,0,1,1));
		visited[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			coordinate cur = q.poll();
			
			if(cur.x == n-1 && cur.y == m-1) { //����������
				result = cur.dis;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int x = cur.x + dx[i]; //�̵��� ��
				int y = cur.y + dy[i]; //�̵��� ��
				
				if(x < 0 || n <= x || y < 0 || m <= y) { //���� �ʰ��� ����
					continue;
				}
				
				if(map[x][y] == '0' && visited[x][y][cur.count] == 0) { //���� �ƴϰ� �ش� ĭ �湮 ������ ��
					q.offer(new coordinate(x, y, cur.count, cur.dis+1, cur.day*-1));
					visited[x][y][cur.count] = visited[cur.x][cur.y][cur.count];
				}else if(map[x][y] == '1' && cur.count + 1 <= k && visited[x][y][cur.count+1] == 0){ //���̰� �ν� ������ ���� k���� ������ �ش� ĭ �湮 ������ ��
					if(cur.day == 1) { // ���� ���
						q.offer(new coordinate(x, y, cur.count+1, cur.dis+1, cur.day*-1));
						visited[x][y][cur.count+1] = visited[cur.x][cur.y][cur.count]+1;
					}else { // ���� ���
						q.offer(new coordinate(x, y, cur.count+1, cur.dis+2, cur.day));
						visited[x][y][cur.count+1] = visited[cur.x][cur.y][cur.count]+2;
					}
					
					
				}
			}
		}
	}
	
	static class coordinate implements Comparable<coordinate> {
		int x; // ���� ��
		int y; //���� ��
		int count; //���� �ν� �� ����
		int dis; // ���� �̵��� �Ÿ�
		int day; //���� �� 1 �� -1
		
		coordinate(int x, int y, int count, int dis, int day) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.dis = dis;
			this.day = day;
		}

		@Override
		public int compareTo(coordinate o) {
			if(this.dis == o.dis) {
				return this.count - o.count;
			}else {
				return this.dis - o.dis;
			}
		}
	}
}
