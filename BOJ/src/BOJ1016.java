import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1016 {
	
		public static void main(String[] args) throws IOException {
			 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 long min = Long.parseLong(st.nextToken());
			 long max = Long.parseLong(st.nextToken());
			 boolean num[] = new boolean[(int) (max - min + 1)]; 
			 long count = 0;
			 
			 ArrayList<Long> square = new ArrayList<>();
			 for(long i = 2; i < Math.sqrt(max) + 1; i++) {
				 square.add(i*i);
			 }
			 
			 for(Long j : square) {
				 double t = (double) min / (double) j;
				 long start = (long) ((j * Math.ceil(t))- min);
				 for(long i = start; i < num.length; i += j) {
					 num[(int) i] = true;
				 }
				 
			 }
			 
			 for(boolean check : num) {
				 if(!check) count++;
			 }
			 
			 System.out.println(count);
			 
		}
}