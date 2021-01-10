import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ14391 {
	static int result = 0;
	static int n;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		String paper[][] = new String[n][m];
		boolean visited[][] = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			paper[i] = br.readLine().split("");
		}
		
		cut(paper, visited, 0);
		System.out.println(result);
		
	}
	static void cut(String[][] p, boolean v[][], int sum) {
		int hIndex = 0;
		int wIndex = 0;
		int hMax = 0;
		int wMax = 0;
		int hSum = 0;
		int wSum = 0;
		
		
		for(int i = 0; i < n; i++) {
			String s = "";
			int temp = 0;
			for(int j = 0; j < m; j++) {
				if(v[i][j]) {
					if(!s.equals("")) {
						hSum += Integer.parseInt(s);
						temp += Integer.parseInt(s);
						s = "";
					}
				}else {
					s += p[i][j];
				}
			}
			if(!s.equals("")) {
				hSum += Integer.parseInt(s);
				temp += Integer.parseInt(s);
				s = "";
			}
			if(hMax < temp) {
				hMax = temp;
				hIndex = i;
			}
		}
		
		for(int j = 0; j < m; j++) {
			String s = "";
			int temp = 0;
			for(int i = 0; i < n; i++) {
				if(v[i][j]) {
					if(!s.equals("")) {
						wSum += Integer.parseInt(s);
						temp += Integer.parseInt(s);
						s = "";
					}
				}else {
					s += p[i][j];
				}
			}
			if(!s.equals("")) {
				wSum += Integer.parseInt(s);
				temp += Integer.parseInt(s);
				s = "";
			}
			if(wMax < temp) {
				wMax = temp;
				wIndex = j;
			}
		}

		if(hMax == 0 && wMax == 0) {
			result = Math.max(result, sum);
			return;
		}
		
		if(hSum < wSum) {
			for(int i = 0; i < n; i++) {
				p[i][wIndex] = "0";
				v[i][wIndex] = true;
			}

			cut(p, v, sum + wMax);
		}else if(hSum > wSum) {
			for(int j = 0; j < m; j++) {
				p[hIndex][j] = "0";
				v[hIndex][j] = true;
			}

			cut(p, v, sum + hMax);
		}else {
			String h[][] = p.clone();
			String w[][] = p.clone();

			for(int i = 0; i < n; i++) {
				w[i][wIndex] = "0";
				v[i][wIndex] = true;
			}
			cut(w, v, sum + wMax);
			
			for(int j = 0; j < m; j++) {
				h[hIndex][j] = "0";
				v[hIndex][j] = true;
			}
			cut(h, v, sum + hMax);
		}
		
	}

}
