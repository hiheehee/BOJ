import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1644 {

	static boolean visited[];
	static ArrayList<Integer> al = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		eratosthenes();
		
		int s = 0;
		int e = 0;
		int sum = 0;
		int count = 0;

		while(true) {
			if(n <= sum) {
				sum -= al.get(s++);
			}else if(e == al.size()) {
				break;
			}else {
				sum += al.get(e++);	
			}
			if(sum == n) count++;
		}
		System.out.println(count);
	}
	
	static void eratosthenes() {
		for(int i = 2; i < visited.length; i++){
			visited[i] = true;
	    }
	    for(int i = 2; i < visited.length; i++){
	    	if(!visited[i]){
	    		continue;
	        }else {
	        	for(int j = i*2; j < visited.length; j+=i){
	        		visited[j] = false;
	        	}
	       }
	   }
	   for(int i = 2; i < visited.length; i++){
			if(visited[i]) al.add(i);
	   }
	}
}
