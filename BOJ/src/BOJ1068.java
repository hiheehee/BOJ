import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1068 {
	static ArrayList<Integer>[] al;
	static int result = 0;
	static int start = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		al = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			al[i] = new ArrayList<Integer>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			int node = Integer.parseInt(st.nextToken());
			if(node != -1) {
				al[node].add(i);
			}else {
				start = i;
			}
		}

		CountLeaf(d);
		
		System.out.println(result);
	}
	
	static void CountLeaf(int del) {
		Queue<Integer> q = new LinkedList<>();
		
		if(del == start) return;
		
		q.add(start);
		
		while(!q.isEmpty()) {
			int node = q.poll();
			int count = 0;
			
			for(Integer i: al[node]) {
				if(i != del) {
					q.add(i);
					count++;
				}
			}
			
			if(count == 0) {
				result++;
			}
		}
	}

}
