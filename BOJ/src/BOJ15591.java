import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ15591 {
	static ArrayList<node> USADO[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		USADO = new ArrayList[N+1];
		
		// map √ ±‚»≠
		for(int i = 0; i < N+1; i++) {
			USADO[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
		
			USADO[p].add(new node(q, r));
			USADO[q].add(new node(p, r));
		}

		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			System.out.println(searchMinPath(v, k));
		}
	}
	

	static int searchMinPath(int ind, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(ind);
		int count = 0;
		
		boolean check[] = new boolean[USADO.length];
		check[ind] = true;
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			for(node next: USADO[cur]) {
				if(!check[next.index] && k <= next.weight) { 
					pq.offer(next.index);
					check[next.index] = true;
					count++;
				}
			}
		}
		
		return count;
	}
	
	static class node {
		private int index;
		private int weight;
		
		node(int index, int weight){
			this.index = index;
			this.weight = weight;
		}
	}
}
