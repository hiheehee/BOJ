import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
	static int n, m, h;
	static int result = Integer.MAX_VALUE;
	static int ladder[][];
	static boolean isFinish = false;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // ���μ��� ����
		m = Integer.parseInt(st.nextToken()); // ���� �� �ִ� ��ġ�� ����
		h = Integer.parseInt(st.nextToken()); // ���μ��� ����
		ladder = new int[h+1][n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); 
			int y = Integer.parseInt(st.nextToken());
			ladder[x][y] = -1; // �������� ����Ǵ� ��ٸ�
			ladder[x][y+1] = 1; // �������� ����Ǵ� ��ٸ�
		}
		
		for(int i = 0; i < 4; i++) { // 0������ 3������ �����غ���
			result = i;
			dfs(1, 0);
			if(isFinish) {
				break;
			}
		}
		
		if(isFinish) {
			System.out.println(result);
		}else {
			System.out.println(-1);
		}
		
	}
	
	static void dfs(int x, int count) {
		if(isFinish) {
			return;
		}
		
		if(count == result) {
			if(check()) {
				isFinish = true;
				
			}
			return;
		}
		
		for(int i = x; i < h+1; i++) {
			for(int j = 1; j < n; j++) {
				if(ladder[i][j] == 0 && ladder[i][j+1] == 0) {
					ladder[i][j] = -1;
					ladder[i][j+1] = 1;
					
					dfs(i, count+1);
					
					ladder[i][j] = 0;
					ladder[i][j+1] = 0;
				}
			}
		}
	}
	
	static boolean check() { // i�� i��°�� ������
		for(int i = 1; i < n+1; i++) { // ���μ��� ����
			int y = i;
			
			for(int j = 1; j < h+1; j++) {
				if(ladder[j][y] == 1) { // �������� �̵�
					y--;
				}else if(ladder[j][y] == -1){ // �������� �̵�
					y++;
				}
			}
			
			if(y != i) {
				return false;
			}
		}
		
		return true;
	}
}