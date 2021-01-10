import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1199 {
	static int circuit[][];
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		circuit = new int[n][n];
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				circuit[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(!isEven()) {
			System.out.print(-1);
		}else {
			EulerCirCuit(0);
		}

	}

	static boolean isEven() {
		for(int i = 0; i < circuit.length; i++) {
			int count = 0;
			for(int j = 0; j < circuit.length; j++) {
				if(circuit[i][j] > 0) {
					count += circuit[i][j];
				}
			}
			if(count%2 == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	static void EulerCirCuit(int here) {
		for(int i = 0; i < circuit.length; i++) {
			while(circuit[here][i] > 0) {
				circuit[here][i]--;
				circuit[i][here]--;
				EulerCirCuit(i);
			}
		}
		System.out.print((here+1)+" ");
		
	}

}
