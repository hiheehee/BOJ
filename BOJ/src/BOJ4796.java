import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4796 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int count = 1;
		
		while(!str.equals("0 0 0")) {
			String vacation[] = str.split(" ");
			int l = Integer.parseInt(vacation[0]);
			int p = Integer.parseInt(vacation[1]);
			int v = Integer.parseInt(vacation[2]);
			int result = Math.min(l, v%p) + (v/p)*l;
			System.out.println("Case "+count+": "+result);
			count++;
			str = br.readLine();
		}
	}

}
