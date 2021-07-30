import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056 {

	static ArrayList<fireBall> map[][];
	static int dxy[][] = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		int result = 0;
		
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                map[i][j] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < K; i++)
			move();

		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(0 < map[i][j].size())
					for(fireBall fb : map[i][j])
						result += fb.m;
		
		System.out.println(result);
	}
	
	static void move() {
		ArrayList<fireBall> tmp[][] = new ArrayList[N][N];
		
		for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                tmp[i][j] = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].size() > 0) {
					for(fireBall cur : map[i][j]) {
						int tr = i + (dxy[cur.d][0] * cur.s%N);
						int tc = j + (dxy[cur.d][1] * cur.s%N);
					
						if(tr < 0) tr += N;
						if(N <= tr) tr -= N;
						if(tc < 0) tc += N;
						if(N <= tc) tc -= N;
						
						tmp[tr][tc].add(new fireBall(cur.m, cur.s, cur.d));
					}
				}
				map[i][j].clear();
			}
		}
		split(tmp);
	}
	
	static void split(ArrayList<fireBall> tmp[][]) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(1 == tmp[i][j].size()) {
					tmp[i][j].clear();
				}else if(2 <= tmp[i][j].size()) {
					int mSum = 0;
					int sSum = 0;
					boolean odd = false;
					boolean even = false;
					
					for(fireBall cur: tmp[i][j]) {
						mSum += cur.m;
						sSum += cur.s;
						if (cur.d%2 == 0) even = true;
						else odd = true;
					}
					
					mSum /= 5;
					sSum /= tmp[i][j].size();
					tmp[i][j].clear();
					if(mSum == 0) continue;
					
					int d = 0;
					if(odd && even) ++d;

					for(; d < 8; d += 2) {
						int tr = i + dxy[d][0] * (sSum%N);
						int tc = j + dxy[d][1] * (sSum%N);
								
						if(tr < 0) tr += N;
						if(N <= tr) tr -= N;
						if(tc < 0) tc += N;
						if(N <= tc) tc -= N;

						tmp[tr][tc].add(new fireBall(mSum, sSum, d));
					}	
				}
			}
		}
		map = tmp;
	}
	
	
	static class fireBall {
		int m;
		int s;
		int d;
		
		fireBall(int m, int s, int d){
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

}
