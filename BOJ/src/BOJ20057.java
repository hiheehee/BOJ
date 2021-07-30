import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ20057 {
	static int total = 0;
	static int dxy[][] = {{0,-1}, {1,0}, {0,1}, {-1,0}}; // (0: ����, 1:����, 2:����, 3:����
	static int sand[][];
	static int n;
	static ArrayList<point>[] tornado = new ArrayList[4]; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sand = new int[n][n];
		
		for(int i = 0; i < 4; i++)
			tornado[i] = new ArrayList<>();

		// input
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				sand[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		tornado[0].add(new point(1,1));
		tornado[1].add(new point(1,7));
		tornado[1].add(new point(2,2));
		tornado[2].add(new point(0,-1));
		tornado[2].add(new point(1,10));
		tornado[3].add(new point(0,5));
		
		move();
		System.out.println(total);
	}
	
	// �� �𳯸�
	static void makeTornado(int x, int y, int d) {
		HashMap<Integer, Integer> hm = new HashMap<>();
		hm.put(1, (int)(sand[x][y]*0.01));
		hm.put(2, (int)(sand[x][y]*0.02));
		hm.put(5, (int)(sand[x][y]*0.05));
		hm.put(7, (int)(sand[x][y]*0.07));
		hm.put(10, (int)(sand[x][y]*0.1));
		int nam = sand[x][y] - 2*(hm.get(1) + hm.get(2) + hm.get(7) + hm.get(10)) - hm.get(5);
		hm.put(-1, nam);
		sand[x][y] = 0;
		boolean check = false; // ����  or ���� : false
		if(d == 1 || d == 3) check = true; // ����  or ���� :  true
			
		for(int i = 0; i < 4; i++) {
			for(point cur : tornado[i]) {
				int tx = 0;
				int ty = 0;
				if(!check) {  // ����  or ���� : false
					tx = x + cur.d;
					ty = y + dxy[d][1]*(i-1);
				}else {  // ����  or ���� :  true
					tx = x + dxy[d][0]*(i-1);
					ty = y + cur.d;
				}
				
				if(cur.d == 0) { // �𳯸��� �𷡰� ��Ī�� �ƴ� ���
					if(tx < 0 || n <= tx || ty < 0 || n <= ty) total += hm.get(cur.r);
					else sand[tx][ty] += hm.get(cur.r);
				}else { // �𳯸��� �𷡰� ��Ī�� ���
					if(check) {
						if(tx < 0 || n <= tx || ty < 0 || n <= ty) total += hm.get(cur.r);
						else sand[tx][ty] += hm.get(cur.r);
						
						ty = y - cur.d;
						
						if(tx < 0 || n <= tx || ty < 0 || n <= ty) total += hm.get(cur.r);
						else sand[tx][ty] += hm.get(cur.r);
					}else {
						if(tx < 0 || n <= tx || ty < 0 || n <= ty) total += hm.get(cur.r);
						else sand[tx][ty] += hm.get(cur.r);
						
						tx = x - cur.d;
						
						if(tx < 0 || n <= tx || ty < 0 || n <= ty) total += hm.get(cur.r);
						else sand[tx][ty] += hm.get(cur.r);
					}
				}
			}
		}
	}
	
	static void move() {
		int sx = n/2;
		int sy = n/2;
		int tx = sx;
		int ty = sy;

		for(int i = 0; i < n; i++) { // ����̵��� ����
			int ind = 0;
			if (i%2 == 1) ind = 2;
			for(int j = 0; j < 2; j++, ind++) { // ����̵� ���̴� 2���� �����δ�. ����&����, ����&����
				for(int k = 0; k < i+1; k++) {
					tx += dxy[ind][0];
					ty += dxy[ind][1];
					if(tx < 0 || n <= tx || ty < 0 || n <= ty) continue; 
					makeTornado(tx, ty, ind);
					if(tx == 0 && ty == 0) return;
				}
			}
		} 
	}
	
	static class point{
		int d; // ����
		int r; // ����
		
		point(int d, int r){
			this.d = d;
			this.r = r;
		}
	}
}