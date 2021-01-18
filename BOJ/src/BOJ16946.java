import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16946 {
	static int n;
	static int m;
	static char map[][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {-1,1,0,0};
	static int group[][];
	static HashMap<Integer, Integer> hm = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // �� ����
		m = Integer.parseInt(st.nextToken()); // �� ����
		map = new char[n][m]; // ���� ǥ��
		group = new int[n][m]; // 0 �׷�ȭ map
		int index = 1; // �׷��ȣ
		
		//map �Է�
		for(int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
	
		// 0 �׷�ȭ �ϱ� => �׷� ��ȣ �ο�, ���� �׷� ���� ����
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == '0' && group[i][j] == 0){
					hm.put(index, bfs(i, j, index));
					index++;
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(mapCount(i,j));
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int mapCount(int X, int Y) {
		int sum = 1;
		HashSet<Integer> hs = new HashSet<>();
		
		if(map[X][Y] == '0') {
			return 0;
		}
		
		for(int i = 0; i < 4; i++) {
			int x = X + dx[i];
			int y = Y + dy[i];
			
			if(x < 0 || n <= x || y < 0 || m <= y) { //���� �ʰ��� ����
				continue;
			}
			
			if(group[x][y] == 0) {
				continue;
			}
			
			if(map[x][y] == '0') { // ���� �ƴҶ�
				hs.add(group[x][y]);
			}

		}
		
		for(int ind: hs) {
			sum += hm.get(ind);
		}
		
		return sum%10;
		
	}
	
	
	static int bfs(int s, int e, int groupNum) {
		int count = 1;
		Queue<coordinate> q = new LinkedList<>();
		q.offer(new coordinate(s, e));
		group[s][e] = groupNum;
		
		while(!q.isEmpty()) {
			coordinate cur = q.poll();

			for(int i = 0; i < 4; i++) {
				int x = cur.x + dx[i];
				int y = cur.y + dy[i];
				
				if(x < 0 || n <= x || y < 0 || m <= y) { //���� �ʰ��� ����
					continue;
				}
				
				if(map[x][y] == '0' && group[x][y] == 0) {
					q.offer(new coordinate(x,y));
					group[x][y] = groupNum;
					count++;
				}
				
			}
		}

		return count;
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

